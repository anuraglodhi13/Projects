package com.nagarro.model;


public class BookDTO {
	private String bookCode;
	private String bookTitle;
	private String addedOn;
	private AuthorDTO author;
	
	public BookDTO() {
	}
	
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}
	public AuthorDTO getAuthor() {
		return author;
	}
	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	
	
}
