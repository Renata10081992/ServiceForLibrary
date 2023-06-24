package com.renata.lesson.dao;

import com.renata.lesson.mapper.PersonMapper;
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
        return jdbcTemplate.query("select * from Person", new PersonMapper());                             //read
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("select * from Person where id=?", new PersonMapper(), id) //read
                .stream().findAny().orElse(null);
    }

    public Person getPersonByFullName(String fullName) {
        return jdbcTemplate.query("select * from Person where full_name=?", new PersonMapper(), fullName) //read
                .stream().findAny().orElse(null);
    }

    public void createPerson(Person person) {
       jdbcTemplate.update("insert  into Person (full_name, year_of_born) values (?, ?)",
                person.getFullName(), person.getYearOfBorn());                                                // create
    }
    public void updatePerson(int id, Person updatedPerson) {
        jdbcTemplate.update("update Person set full_name=?, year_of_born=? where id=?", updatedPerson.getFullName(),
        updatedPerson.getYearOfBorn(), id);                                                                   // update
    }

    public void deletePerson(int id) {
        jdbcTemplate.update(("delete from Person where id=?"), id);                                           //delete
    }

    public List<Person> getBooksByPersonId (int id) {
        return jdbcTemplate.query("select * from Book where id_person=?", new PersonMapper(), id);
    }

}
