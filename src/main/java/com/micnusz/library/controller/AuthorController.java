package com.micnusz.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micnusz.library.Book;
import com.micnusz.library.service.LibraryService;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

    private final LibraryService libraryService;

    public AuthorController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<String> getAllAuthors() {
        return libraryService.getAllAuthors();
    }

    @GetMapping("/{author}/books")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return libraryService.getBookByAuthor(author);
    }

}
