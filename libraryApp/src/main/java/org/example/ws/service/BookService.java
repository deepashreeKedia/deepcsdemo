package org.example.ws.service;

import org.example.ws.dto.*;
import org.example.ws.model.Book;

import java.util.List;

public interface BookService {

    RegisterBookResponse registerBook(RegisterBookRequest registerBookRequest);

    void rentBook(RentBookRequest rentBookRequest) throws Exception;

    List<Book> getBookByTitle(String title) throws Exception;
}
