package org.example.ws.service;


import org.example.ws.constant.AppConstant;
import org.example.ws.dto.*;
import org.example.ws.model.Book;
import org.example.ws.model.Rent;
import org.example.ws.model.Student;
import org.example.ws.repository.BookRepository;
import org.example.ws.repository.RentRepository;
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

    @Autowired
    private RentRepository rentRepository;

    @Override
    public RegisterBookResponse registerBook(RegisterBookRequest registerBookRequest) {

        String bookId = UUID.randomUUID().toString();



        Book newBook = new Book();
        newBook.setAuthor(registerBookRequest.getAuthor());
        newBook.setTitle(registerBookRequest.getTitle());
        newBook.setId(bookId);
        newBook.setRegisteredOn(new Date());

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
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        Student student;
        if(!optionalStudent.isPresent()) {
            throw new Exception("Student is not valid");
        }
        student = optionalStudent.get();

        // check if book title is available
        String bookId = rentBookRequest.getBookId();
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book;

        if(!optionalBook.isPresent()) {
            throw new Exception("Book is not available currently to rent");
        } else {
            book = optionalBook.get();
        }
        // if above conditions are met, rent book success

        Rent rent = new Rent();
        rent.setId(UUID.randomUUID().toString());
        rent.setBook(book);
        rent.setStudent(student);
        rent.setRentedOn(new Date());

        rentRepository.save(rent);
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
