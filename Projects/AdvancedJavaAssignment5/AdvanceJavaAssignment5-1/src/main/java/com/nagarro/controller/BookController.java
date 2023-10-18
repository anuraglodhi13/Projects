package com.nagarro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nagarro.constants.Constants;
import com.nagarro.model.AuthorDTO;
import com.nagarro.model.BookDTO;
import com.nagarro.service.RestClientService;

@Controller
public class BookController {
	@Autowired
	RestClientService restClientService;

	@Autowired
	AuthorDTO author;

	@Autowired
	BookDTO book;

	@Autowired
	ModelAndView mv;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addingBook(@RequestParam(Constants.BOOK_CODE) String bookCode,
			@RequestParam(Constants.BOOK_NAME) String bookTitle, @RequestParam(Constants.AUTHOR_ID) Integer authorId,
			@RequestParam(Constants.ADDED_ON) String addedOn, HttpServletRequest request)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		HttpSession session = request.getSession();
		if(restClientService.getBook(bookCode) != null) {
			session.setAttribute("book-already-exist","true");
			return Constants.REDIRECT_LISTING;
		}
		author.setAuthorId(authorId);
		book.setAddedOn(addedOn);
		book.setAuthor(author);
		book.setBookCode(bookCode);
		book.setBookTitle(bookTitle);
		HttpStatus addedBookStatus = restClientService.addBook(book);
		System.out.println("Book added successfully status received from server is : " + addedBookStatus);
		session.setAttribute("book-added","true");
		return Constants.REDIRECT_LISTING;
	}

	@RequestMapping(value = "/listing", method = RequestMethod.GET)
	public ModelAndView setBookList() throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<BookDTO> li = restClientService.getAllBooks();
		mv.addObject(Constants.BOOKS_IN_TABLE, li);
		mv.setViewName(Constants.LISTING_JSP_FILE);
		return mv;
	}

	@RequestMapping(value = "/edit/{bookCode}", method = RequestMethod.GET)
	public ModelAndView routeToEdit(@PathVariable String bookCode)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		BookDTO book = restClientService.getBook(bookCode);
		if (book == null) { // if someone directly hit the edit/bookCode and book doesn't exist
			mv.setViewName(Constants.LOGIN_JSP_FILE);
			return mv;
		}
		List<AuthorDTO> li, newList = new ArrayList<>();

		li = restClientService.getAllAuthor();
		Iterator<AuthorDTO> itr = li.iterator();
		while (itr.hasNext()) { // to remove the author from dropdown and show edited author at top.
			AuthorDTO author = itr.next();
			if (!author.getAuthorId().equals(book.getAuthor().getAuthorId())) {
				newList.add(author);
			}
		}

		mv.addObject(Constants.AUTHOR_FIX_ID, book.getAuthor().getAuthorId());
		mv.addObject(Constants.AUTHOR_FIX_NAME, book.getAuthor().getAuthorName());
		mv.addObject(Constants.AUTHOR_LIST, newList);
		mv.addObject(Constants.BOOK_CODE, book.getBookCode());
		mv.addObject(Constants.BOOK_NAME, book.getBookTitle());
		mv.setViewName(Constants.EDIT_JSP_FILE);
		return mv;
	}

	@RequestMapping(value = "/edit/{bookCode}", method = RequestMethod.POST)
	public String editBook(@RequestParam(Constants.BOOK_CODE) String bookCode,
			@RequestParam(Constants.BOOK_NAME) String bookTitle, @RequestParam(Constants.AUTHOR_ID) Integer authorId,
			@RequestParam(Constants.ADDED_ON) String addedOn,HttpServletRequest request)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		author.setAuthorId(authorId);
		book.setAddedOn(addedOn);
		book.setAuthor(author);
		book.setBookCode(bookCode);
		book.setBookTitle(bookTitle);
		HttpSession session = request.getSession();
		restClientService.editBook(bookCode, book);
		session.setAttribute("book-edited", "true");
		return Constants.REDIRECT_LISTING;
	}

	@RequestMapping(value = "/delete/{bookCode}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable String bookCode,HttpServletRequest request)
			throws ParseException, JsonParseException, JsonMappingException, IOException {
		HttpSession session = request.getSession();
		restClientService.deleteBook(bookCode);
		session.setAttribute("book-deleted", "true");
		return Constants.REDIRECT_LISTING;
	}
	
}