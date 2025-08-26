package com.micnusz.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micnusz.library.Book;
import com.micnusz.library.repository.BookRepository;

@Service
public class LibraryService {

    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<String> getAllAuthors() {
        return bookRepository.findAllAuthors();
    }

    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);

    }
}