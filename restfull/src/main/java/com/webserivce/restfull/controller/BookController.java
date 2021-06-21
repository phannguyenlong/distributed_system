package com.webserivce.restfull.controller;

import com.webserivce.restfull.resources.Author;
import com.webserivce.restfull.resources.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    // GET: http://localhost:8080/getbook?name=heello
    @GetMapping("/getbook")
    public Book getBook(@RequestParam(name = "name", defaultValue = "Long") String name) {
        Author author = new Author(name, 21);
        Book book = new Book("The end of the world", "VGU", author);
        return book;
    }

    // POST: http://localhost:8080/addbook with Content-Type:application/json
    @PostMapping("/addbook")
    public void addbook(@RequestBody Book book)
    {
        System.out.println("=============Data from POST /addbook==============");
        System.out.println(book);
    }

}
