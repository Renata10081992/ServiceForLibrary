package com.renata.lesson.model;

import jakarta.validation.constraints.*;

public class Person {

    private int id;
    @NotEmpty(message = "name not should be empty")
    @Size(min=2, max=30, message= "name should be between 2 and 30 chars")
    private String fullName;
    @NotEmpty
    @Pattern(regexp = "\\d{2}.\\d{2}.\\d{4}", message = "format is 00.00.0000")
    private int yearOfBorn;

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
