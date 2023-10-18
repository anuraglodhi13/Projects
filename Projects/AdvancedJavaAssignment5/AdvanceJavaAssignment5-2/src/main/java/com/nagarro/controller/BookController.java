package com.nagarro.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.nagarro.Constants.Constant;
import com.nagarro.model.Author;
import com.nagarro.model.Book;
import com.nagarro.response.ResponseHandler;
import com.nagarro.service.BookSevice;

@RestController
public class BookController {
	@Autowired
	private BookSevice bookService;

	@GetMapping("/Library/books")
	public ResponseEntity<Object> getAllBooks() {
		try {
			List<Book> books = bookService.findAll();
			if (books.isEmpty() || books.size() == 0) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, books);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@GetMapping("/Library/book/{bookCode}")
	public ResponseEntity<Object> getAuthor(@PathVariable String bookCode) {
		try {
			Optional<Book> searchedBook = bookService.findById(bookCode);
			if (searchedBook.isEmpty()) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, searchedBook.get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PostMapping("/Library/book")
	public ResponseEntity<Object> createBook(@RequestBody Book book) {
		try {
			Optional<Book> existBook = bookService.findById(book.getBookCode());
			if (existBook.isEmpty()) {
				Book savedBook = bookService.save(book);
				if (savedBook != null) {
					return ResponseHandler.generateResponse(Constant.SUCCESS_ADD_MESSAGE, HttpStatus.CREATED,
							savedBook);
				}
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_BOOK_EXIST, HttpStatus.CONFLICT, null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PutMapping("/Library/book/{bookCode}")
	public ResponseEntity<Object> updateBook(@RequestBody Book book, @PathVariable String bookCode) {
		try {
			Optional<Book> existBook = bookService.findById(bookCode);
			if (existBook.isPresent()) {
				Book searchedBook = existBook.get();
				searchedBook.setAuthor(book.getAuthor());
				searchedBook.setBookTitle(book.getBookTitle());
				Book updatedBook = bookService.save(searchedBook);
				return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedBook);
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_BOOK_NOT_PRESENT, HttpStatus.CONFLICT,
					null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/Library/book/{bookCode}")
	public ResponseEntity<Object> deleteBook(@PathVariable String bookCode) {
		try {
			Optional<Book> deletedBook = bookService.findById(bookCode);
			if (deletedBook.isPresent()) {
				bookService.deleteById(bookCode);
				return ResponseHandler.generateResponse(Constant.DELETE_SUCCESS_MESSAGE, HttpStatus.OK, deletedBook);
			}
			return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT,null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
}
