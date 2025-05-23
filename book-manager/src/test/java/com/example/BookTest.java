package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @BeforeEach
    public void resetBooks() throws Exception {
        // Clear static books map using reflection (test isolation)
        Field booksField = Book.class.getDeclaredField("books");
        booksField.setAccessible(true);
        ((Map<?, ?>) booksField.get(null)).clear();

        Field nextIdField = Book.class.getDeclaredField("nextId");
        nextIdField.setAccessible(true);
        nextIdField.set(null, 1);
    }

    @Test
    public void testAddBook() {
        Book.addBook("Test Title", "Test Author");
        assertEquals(1, getBooks().size());
    }

    @Test
    public void testRemoveBook() {
        Book.addBook("Title", "Author");
        Book.removeBook(1);
        assertEquals(0, getBooks().size());
    }

    @Test
    public void testChangeBookStatus() {
        Book.addBook("Title", "Author");
        Book.changeBookStatus(1);
        Book changedBook = getBooks().get(1);
        assertTrue(changedBook.isBorrowed());
    }

    private Map<Integer, Book> getBooks() {
        try {
            Field booksField = Book.class.getDeclaredField("books");
            booksField.setAccessible(true);
            return (Map<Integer, Book>) booksField.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
