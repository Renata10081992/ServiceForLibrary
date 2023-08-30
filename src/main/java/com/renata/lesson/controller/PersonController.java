package com.renata.lesson.controller;

import com.renata.lesson.dao.PersonDao;
import com.renata.lesson.model.Person;
import com.renata.lesson.valid.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonDao personDao;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String getPersons(Model model) {
        model.addAttribute("persons", personDao.getPersons());
        return "person/persons";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDao.getPersonById(id));
        model.addAttribute("books", personDao.getBooksByPersonId(id));
        return "person/onePerson";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "person/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("newPerson") @Valid Person person,
                               BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "person/new";
        personDao.createPerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("editPerson", personDao.getPersonById(id));
        return "person/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("editPerson") @Valid Person person, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "person/edit";
        personDao.updatePerson(id, person);
        return "redirect:/persons";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDao.deletePerson(id);
        return "redirect:/persons";
    }
}
