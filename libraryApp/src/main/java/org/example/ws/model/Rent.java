package org.example.ws.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = true)
    private Book book;

    private Date rentedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getRentedOn() {
        return rentedOn;
    }

    public void setRentedOn(Date rentedOn) {
        this.rentedOn = rentedOn;
    }
}
