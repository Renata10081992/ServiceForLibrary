package com.renata.lesson.valid;


import com.renata.lesson.dao.PersonDao;
import com.renata.lesson.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDao personDao;

    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDao.getPersonByFullName(person.getFullName()) != null) {
            errors.rejectValue("fullName", "", "This fullName is already taken");
        }
    }
}
