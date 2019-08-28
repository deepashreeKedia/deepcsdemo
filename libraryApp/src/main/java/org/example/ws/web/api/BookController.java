package org.example.ws.web.api;

import org.example.ws.dto.*;
import org.example.ws.model.Book;
import org.example.ws.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(
            value = "/api/registerBook",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterBookResponse> registerBook(@Valid  @RequestBody RegisterBookRequest registerBookRequest) {

        RegisterBookResponse response = bookService.registerBook(registerBookRequest);

        return new ResponseEntity<RegisterBookResponse>(response,
                HttpStatus.OK);
    }


    @RequestMapping(
            value = "/api/rentBook",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerBook(@Valid @RequestBody RentBookRequest rentBookRequest) throws Exception{

        bookService.rentBook(rentBookRequest);

        return new ResponseEntity<String>("book is rented successfully",
                HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/findBookByTitle",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> findBookByTitle(@RequestParam String title) throws Exception{

        List<Book> bookList = bookService.getBookByTitle(title);

        return new ResponseEntity<List<Book>>(bookList,
                HttpStatus.OK);
    }

}
