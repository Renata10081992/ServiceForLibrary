package com.renata.lesson.controller;

import com.renata.lesson.dao.BookDao;
import com.renata.lesson.dao.PersonDao;
import com.renata.lesson.model.Book;
import com.renata.lesson.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookDao.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String getIdBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.getBook(id));

        Person p = bookDao.getBookOwner(id);
        if(p!=null)
            model.addAttribute("owner", p);
        else
            model.addAttribute("people", personDao.getPersons());
        return "books/oneBook";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("newBook", new Book());                 //create1
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("newBook") Book book) {
        bookDao.createBook(book);                                            //create2
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("updateBook", bookDao.getBook(id));
        return "books/edit";                                                      //update1
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("updateBook") Book book, @PathVariable("id") int id) {
        bookDao.updateBook(id, book);
        return "redirect:/books";                                              //update2
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.deleteBook(id);
        return "redirect:/books";                                              //delete
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable ("id") int id) {
        bookDao.release(id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable ("id") int id, @ModelAttribute("person") Person p) {
        bookDao.assign(id,p);
        return "redirect:/books/"+id;
    }
}

