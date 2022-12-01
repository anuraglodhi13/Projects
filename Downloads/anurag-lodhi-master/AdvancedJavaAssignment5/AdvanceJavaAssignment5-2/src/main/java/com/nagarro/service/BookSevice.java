package com.nagarro.service;

import java.util.List;
import java.util.Optional;

import com.nagarro.model.Book;

public interface BookSevice {
	Book save(Book book);
	void deleteById(String bookCode);
	Optional<Book> findById(String bookCode);
	List <Book> findAll();
}
