package com.nagarro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.model.Author;
import com.nagarro.repository.AuthorRepository;

@Service
public class AuthorServiceImp implements AuthorService{

	@Autowired
    private AuthorRepository authorRepository;
	
	@Override
	public Author save(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public void deleteById(Integer id) {
		authorRepository.deleteById(id);
		
	}

	@Override
	public Optional<Author> findById(Integer id) {
		return authorRepository.findById(id);
	}

	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}
	

}
