package com.renata.lesson.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int idBook;
    private int idPerson;

    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 50, message = "Длина должна быть от 2 до 50 символов")
    private String title;
    @NotEmpty(message = "Имя не может быть пустым")
    private String author;
    @Min(value = 1500, message = "Не ранее 1500 года издания")
    @Max(value = 2023, message = "Не позднее 2023 года издания")
    private int yearOfPublish;

    public Book() {
    }

    public Book(String title, String author, int yearOfPublish) {
        this.title = title;
        this.author = author;
        this.yearOfPublish = yearOfPublish;
    }

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
