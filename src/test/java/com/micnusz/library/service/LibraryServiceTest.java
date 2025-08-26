package com.micnusz.library.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.micnusz.library.Book;
import com.micnusz.library.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    void testAddBook() {
        Book bookToSave = new Book("New Book", "New Author", "Sci-Fi", 300);
        Book savedBook = new Book("New Book", "New Author", "Sci-Fi", 300);
        savedBook.setId(1L);

        when(bookRepository.save(bookToSave)).thenReturn(savedBook);

        // ACT
        Book result = libraryService.addBook(bookToSave);

        assertNotNull(result.getId(), "Book should have ID after saving");
        assertEquals(1L, result.getId(), "Book ID should be 1");
        assertEquals("New Book", result.getTitle(), "Title should match");
        assertEquals("New Author", result.getAuthor(), "Author should match");
        assertEquals("Sci-Fi", result.getGenre(), "Genre should match");
        assertEquals(300, result.getPages(), "Pages should match");

        verify(bookRepository, times(1)).save(bookToSave);
        verify(bookRepository, never()).delete(any());

    }

    @Test
    void testGetAllAuthors() {
        when(bookRepository.findAllAuthors()).thenReturn(Arrays.asList("Author1", "Author2"));

        List<String> result = libraryService.getAllAuthors();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAllAuthors();

    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book("title1", "author1", "genre1", 400);
        Book book2 = new Book("title2", "author2", "genre2", 200);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> result = libraryService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals("title1", result.get(0).getTitle());
        assertEquals("title2", result.get(1).getTitle());
        verify(bookRepository, times(1)).findAll();

    }

    @Test
    void testGetBookByAuthor() {
        String authorName = "Stephen King";
        Book book1 = new Book("title1", authorName, "horror", 200);
        Book book2 = new Book("title2", authorName, "horror", 100);

        when(bookRepository.findByAuthor(authorName)).thenReturn(Arrays.asList(book1, book2));

        List<Book> result = libraryService.getBookByAuthor(authorName);

        assertEquals(2, result.size(), "Should return 2 books");
        assertEquals("title1", result.get(0).getTitle(), "First book title should match");
        assertEquals("title2", result.get(1).getTitle(), "Second book title should match");
        assertEquals(authorName, result.get(0).getAuthor(), "Author name should match");
        assertEquals(authorName, result.get(1).getAuthor(), "Author name should match");

        verify(bookRepository, times(1)).findByAuthor(authorName);

    }
}
