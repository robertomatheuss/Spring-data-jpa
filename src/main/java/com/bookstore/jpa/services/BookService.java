package com.bookstore.jpa.services;

import com.bookstore.jpa.controller.exceptions.BookException;
import com.bookstore.jpa.dto.BookRecordDTO;
import com.bookstore.jpa.dto.PublisherRecordDTO;
import com.bookstore.jpa.model.BookModel;
import com.bookstore.jpa.model.PublisherModel;
import com.bookstore.jpa.model.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional
    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public BookModel getBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    @Transactional
    public BookModel saveBook(BookRecordDTO bookRecordDTO){
        BookModel book = new BookModel();
        if (bookRepository.findAll().stream().anyMatch(bookList-> bookList.getTitle().equals(bookRecordDTO.title()))){
            throw new BookException("There is already a book with this title.");
        }
        book.setTitle(bookRecordDTO.title());
        book.setPublisher(publisherRepository.findById(bookRecordDTO.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDTO.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel review = new ReviewModel();
        review.setBook(book);
        review.setComment(bookRecordDTO.reviewComment());
        book.setReview(review);
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public BookModel updatePublisherInBook(UUID BookId, PublisherRecordDTO publisherDTO){
        BookModel book = bookRepository.findById(BookId).orElseThrow(() -> new NoSuchElementException("Book not found"));
        PublisherModel newPublisher = publisherRepository.findByName(publisherDTO.publisherName())
                .orElseThrow(() -> new NoSuchElementException("Name provided is incorrect"));
        book.setPublisher(newPublisher);
        return bookRepository.save(book);
    }
}
