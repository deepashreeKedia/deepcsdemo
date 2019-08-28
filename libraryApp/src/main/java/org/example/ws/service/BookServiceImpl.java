package org.example.ws.service;


import org.example.ws.constant.AppConstant;
import org.example.ws.dto.*;
import org.example.ws.model.Book;
import org.example.ws.model.Student;
import org.example.ws.repository.BookRepository;
import org.example.ws.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public RegisterBookResponse registerBook(RegisterBookRequest registerBookRequest) {

        String bookId = UUID.randomUUID().toString();


        Book newBook = new Book();
        newBook.setAuthor(registerBookRequest.getAuthor());
        newBook.setTitle(registerBookRequest.getTitle());
        newBook.setId(bookId);

        Book registeredBook = bookRepository.save(newBook);

        //prepare response
        RegisterBookResponse response = new RegisterBookResponse();
        response.setAuthor(registeredBook.getAuthor());
        response.setTitle(registeredBook.getTitle());
        response.setBookId(registeredBook.getId());

        return response;

    }

    @Override
    public  void rentBook(RentBookRequest rentBookRequest) throws Exception {

        // check if user is valid and not blacklisted

        String studentId = rentBookRequest.getStudentId();
        Optional<Student> student = studentRepository.findById(studentId);
        if(student == null || !AppConstant.ACTIVE.equals(student.get().getStatus())) {
            throw new Exception("Student is not valid");
        }
        // check if user has no more than 2 books rented
        if(student.get().getNoOfBooks() == 2) {
            throw new Exception("Student can not rent more than 2 books at a time");
        }
        // check if book title is available and not rented by anyone else
        String bookId = rentBookRequest.getBookId();
        Optional<Book> book = bookRepository.findById(bookId);

        if(book == null || book.get().getRentedBy() != null) {
            throw new Exception("Book is not available currently to rent");
        }
        // if above conditions are met, rent book success

        synchronized (this) {
            book.get().setRentedBy(studentId);
            book.get().setRentedOn(new Date());

            student.get().setNoOfBooks(student.get().getNoOfBooks() + 1);

            bookRepository.save(book.get());
            studentRepository.save(student.get());
        }
    }

    @Override
    public List<Book> getBookByTitle(String title) throws Exception{

        List<Book> bookList =  bookRepository.findBookByTitle(title);
        if(bookList.isEmpty()) {
            throw new Exception("no matching title found");
        }

        return bookList;
    }

}
