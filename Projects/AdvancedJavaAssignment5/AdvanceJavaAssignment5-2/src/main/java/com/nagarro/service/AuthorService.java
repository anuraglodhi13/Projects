package com.nagarro.service;

import java.util.List;
import java.util.Optional;

import com.nagarro.model.Author;

public interface AuthorService {
	Author save(Author author);
	void deleteById(Integer id);
	Optional<Author> findById(Integer id);
	List <Author> findAll();
}
