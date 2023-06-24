package com.renata.lesson.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int idBook;
    private int idPerson;

    @NotEmpty(message = "name not should be empty")
    @Size(min=2, max=50, message= "name should bw between 2 and 50 chars")
    private String title;
    @NotEmpty(message = "name not should be empty")
    private String author;
    @Min(value=1, message= "not minus, only >0")
    @Max(value = 2023, message= "not > 2023")
    private int yearOfPublish;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }
}
