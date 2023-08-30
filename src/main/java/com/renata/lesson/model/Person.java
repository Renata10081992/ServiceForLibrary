package com.renata.lesson.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Длина должна быть от 2 до 30 символов")
    private String fullName;
    @Min(value = 1900, message = "Год должен быть больше 1900")
    @Max(value = 2023, message = "Год должен быть меньше 2023")
    private int yearOfBorn;

    public Person() {

    }

    public Person(String fullName, int yearOfBorn) {
        this.fullName = fullName;
        this.yearOfBorn = yearOfBorn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }
}
