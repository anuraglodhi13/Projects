package com.nagarro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.nagarro.Constants.Constant;
import com.nagarro.model.Author;
import com.nagarro.response.ResponseHandler;
import com.nagarro.service.AuthorService;

@RestController
public class AuthorController {
	@Autowired
	private AuthorService authorService;

	@GetMapping("/Library/authors")
	public ResponseEntity<Object> getAllAuthors() {
		try {
			List<Author> authors = authorService.findAll();
			if (authors.isEmpty() || authors.size() == 0) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, authors);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@GetMapping("/Library/author/{id}")
	public ResponseEntity<Object> getAuthor(@PathVariable Integer id) {
		try {
			Optional<Author> searchedAuthor = authorService.findById(id);
			if (searchedAuthor.isEmpty()) {
				return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.NO_CONTENT, null);
			}
			return ResponseHandler.generateResponse(Constant.SUCCESS_GET_MESSAGE, HttpStatus.OK, searchedAuthor.get());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@PostMapping("/Library/author")
	public ResponseEntity<Object> createAuthor(@RequestBody Author author) {
		try {
			Optional<Author> existAuthor = authorService.findById(author.getAuthorId());
			if(existAuthor.isEmpty()) {
				Author savedAuthor = authorService.save(author);
				if (savedAuthor != null) {
					return ResponseHandler.generateResponse(Constant.SUCCESS_ADD_MESSAGE, HttpStatus.CREATED, savedAuthor);
				}
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_AUTHOR_EXIST, HttpStatus.CONFLICT, null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@PutMapping("/Library/author/{id}")
	public ResponseEntity<Object> updateAuthor(@RequestBody Author author, @PathVariable Integer id) {
		try {
			Optional<Author> existAuthor = authorService.findById(id);
			if(existAuthor.isPresent()) {
				Author searchedAuthor = existAuthor.get();
				searchedAuthor.setAuthorName(author.getAuthorName());
				Author updatedAuthor = authorService.save(searchedAuthor);
				return ResponseHandler.generateResponse(Constant.UPDATE_SUCCESS_MESSAGE, HttpStatus.OK, updatedAuthor);
			}
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE_AUTHOR_NOT_PRESENT, HttpStatus.CONFLICT, null);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(Constant.CONFLICT_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

	@DeleteMapping("/Library/author/{id}")
	public ResponseEntity<Object> deleteAuthor(@PathVariable Integer id) {
		try {
			Optional<Author> deletedAuthor = authorService.findById(id);
			if(deletedAuthor.isPresent()) {
			authorService.deleteById(id);
			return ResponseHandler.generateResponse(Constant.DELETE_SUCCESS_MESSAGE, HttpStatus.OK, deletedAuthor);
			}
			return ResponseHandler.generateResponse(Constant.NO_DATA_MESSAGE, HttpStatus.CONFLICT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
}
