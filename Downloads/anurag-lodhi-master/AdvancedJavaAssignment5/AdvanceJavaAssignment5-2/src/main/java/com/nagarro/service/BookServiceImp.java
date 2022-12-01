package com.nagarro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.model.Book;
import com.nagarro.repository.BookRepository;

@Service
public class BookServiceImp implements BookSevice{
	
	@Autowired
    private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		 return bookRepository.save(book);
	}

	@Override
	public void deleteById(String bookCode) {
		bookRepository.deleteById(bookCode);
		
	}

	@Override
	public Optional<Book> findById(String bookCode) {
		return bookRepository.findById(bookCode);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

}
