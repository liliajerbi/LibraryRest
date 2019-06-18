package com.example.challengeRest.Library.controllers;

import com.example.challengeRest.Library.entity.Book;
import com.example.challengeRest.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book showOneBook(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchBook = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchBook, searchBook);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book bookUpdate = bookRepository.findById(id).get();
        bookUpdate.setTitle(book.getTitle());
        bookUpdate.setAuthor(book.getAuthor());
        bookUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookUpdate);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
