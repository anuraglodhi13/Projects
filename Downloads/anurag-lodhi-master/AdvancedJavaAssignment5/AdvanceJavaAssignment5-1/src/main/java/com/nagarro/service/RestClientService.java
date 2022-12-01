package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.constants.Constants;
import com.nagarro.constants.UriConstants;
import com.nagarro.model.AuthorDTO;
import com.nagarro.model.BookDTO;

@Component
public class RestClientService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper mapper;

	public HttpStatus addBook(BookDTO book) {
		try {
			ResponseEntity<BookDTO> bookSaved = restTemplate.postForEntity(UriConstants.SERVER_URI + UriConstants.ADD_BOOK, book,
					BookDTO.class);
			return bookSaved.getStatusCode();
		} catch(Exception e) {
			return null;
		}
	}

	public List<AuthorDTO> getAllAuthor() throws JsonParseException, JsonMappingException, IOException, ParseException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		try {
			// endpoint hit
			String result = restTemplate
					.exchange(UriConstants.SERVER_URI + UriConstants.GET_ALL_AUTHORS, HttpMethod.GET, entity, String.class).getBody();

			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(result);
			JSONArray resultArray = (JSONArray) json.get("data");
			List<AuthorDTO> authors = Arrays.asList(mapper.readValue(resultArray.toString(), AuthorDTO[].class));
			return authors;
		} catch(Exception e) {
			return null;
		}
		
	}

	public List<BookDTO> getAllBooks() throws JsonParseException, JsonMappingException, IOException, ParseException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		try {
			// endpoint hit
			String result = restTemplate
					.exchange(UriConstants.SERVER_URI + UriConstants.GET_ALL_BOOKS, HttpMethod.GET, entity, String.class).getBody();

			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(result);

			JSONArray resultArray = (JSONArray) json.get("data");
			List<BookDTO> books = Arrays.asList(mapper.readValue(resultArray.toString(), BookDTO[].class));
			return books;
		} catch(Exception e) {
			return null;
		}
		
	}

	public BookDTO getBook(String bookCode)
			throws JsonParseException, JsonMappingException, IOException, ParseException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		try {
			// endpoint hit
			String result = restTemplate
					.exchange(UriConstants.SERVER_URI + UriConstants.GET_BOOK + bookCode, HttpMethod.GET, entity, String.class)
					.getBody();
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(result);

			BookDTO book = mapper.readValue(json.get("data").toString(), BookDTO.class);
			return book;
		} catch(Exception e) {
			return null;
		}
	}

	public void editBook(String bookCode, BookDTO book)
			throws JsonParseException, JsonMappingException, IOException, ParseException {
		// endpoint hit
		restTemplate.put(UriConstants.SERVER_URI + UriConstants.EDIT_BOOK + bookCode, book, BookDTO.class);
		System.out.println("Book with code " + book.getBookCode() + " is edited with details : " + " Book Title : "
				+ book.getBookTitle() + " Book Author Name : " + book.getAuthor().getAuthorName());
	}

	public void deleteBook(String bookCode)
			throws JsonParseException, JsonMappingException, IOException, ParseException {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.BOOK_CODE, bookCode);
		// endpoint hit
		
		restTemplate.delete(UriConstants.SERVER_URI + UriConstants.DELETE_BOOK + bookCode, params);
		System.out.println("Book with code " + bookCode + " is deleted succesfully ");
	}
}
