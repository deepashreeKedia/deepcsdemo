package org.example.ws.service;

import java.util.List;

import org.example.ws.AbstractTest;
import org.example.ws.dto.RegisterBookRequest;
import org.example.ws.model.Book;
import org.example.ws.repository.BookRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test methods for the BookService.
 *
 */
public class BookServiceTest extends AbstractTest {

    @Autowired
    private BookService service;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository.deleteAll();
    }

    @After
    public void tearDown() {
        // clean up after each test method
        bookRepository.deleteAll();
    }

    @Test
    public void testRegisterBook() {

        RegisterBookRequest request = new RegisterBookRequest();
        request.setAuthor("abc");
        request.setTitle("eragon");

        service.registerBook(request);

        List<Book> booklist = bookRepository.findAll();

        Assert.assertNotNull("failure - expected not null", booklist);
        Assert.assertEquals("failure - expected list size", 1, booklist.size());

    }
}