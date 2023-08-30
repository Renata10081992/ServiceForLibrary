package com.renata.lesson.dao;

import com.renata.lesson.mapper.BookMapper;
import com.renata.lesson.mapper.PersonMapper;
import com.renata.lesson.model.Book;
import com.renata.lesson.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("select * from Book", new BookMapper());
    }

    public Book getBook(int id) {
        return jdbcTemplate.query("select * from Book where id_book=?", new BookMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void createBook(Book book) {
        jdbcTemplate.update("insert into Book (titled, author, year_of_publish) values ( ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublish());
    }


    public void updateBook(int id, Book updatedBook) {
        jdbcTemplate.update("update Book set  titled=?, author=?, year_of_publish=? where id_book=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfPublish(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from Book where id_book=?", id);
    }

    public Person getBookOwner(int id) {
        return jdbcTemplate.query("select Person.* from Book join Person on book.id_person=person.id_person "
                + "where book.id_book=?", new PersonMapper(), id).stream().findAny().orElse(null);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book set id_person=null where id_book=?", id);
    }

    public void assign(int id, Person p) {
        jdbcTemplate.update("update Book set id_person=? where id_book=?", p.getId(), id);
    }

}

