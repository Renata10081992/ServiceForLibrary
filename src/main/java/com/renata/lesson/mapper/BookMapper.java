package com.renata.lesson.mapper;

import com.renata.lesson.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setIdBook(rs.getInt("id_book"));
        book.setIdPerson(rs.getInt("id_person"));
        book.setTitle(rs.getString("titled"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfPublish(rs.getInt("year_of_publish"));
        return book;
    }
}
