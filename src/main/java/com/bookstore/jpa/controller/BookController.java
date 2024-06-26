package com.bookstore.jpa.controller;

import com.bookstore.jpa.dto.BookRecordDTO;
import com.bookstore.jpa.dto.PublisherRecordDTO;
import com.bookstore.jpa.model.BookModel;
import com.bookstore.jpa.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody @Valid BookRecordDTO bookRecordDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisherInBook(@PathVariable(value = "id") UUID BookId,
                                             @RequestBody @Valid PublisherRecordDTO publisherDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updatePublisherInBook(BookId,publisherDTO));

    }
}
