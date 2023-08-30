package com.renata.lesson.dao;

import com.renata.lesson.mapper.BookMapper;
import com.renata.lesson.mapper.PersonMapper;
import com.renata.lesson.model.Book;
import com.renata.lesson.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> getPersons() {
        return jdbcTemplate.query("select * from Person", new PersonMapper());
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("select * from Person where id_person=?", new PersonMapper(), id)
                .stream().findAny().orElse(null);
    }

    public Person getPersonByFullName(String fullName) {
        return jdbcTemplate.query("select * from Person where full_name=?", new PersonMapper(), fullName)
                .stream().findAny().orElse(null);
    }

    public void createPerson(Person person) {
       jdbcTemplate.update("insert  into Person (full_name, year_of_born) values (?, ?)",
                person.getFullName(), person.getYearOfBorn());
    }
    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("update Person set full_name=?, year_of_born=? where id_person=?", updatedPerson.getFullName(),
        updatedPerson.getYearOfBorn(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update(("delete from Person where id_person=?"), id);
    }

    public List<Book> getBooksByPersonId (int id) {
        return jdbcTemplate.query("select * from Book where id_person=?", new BookMapper(), id);
    }

}
