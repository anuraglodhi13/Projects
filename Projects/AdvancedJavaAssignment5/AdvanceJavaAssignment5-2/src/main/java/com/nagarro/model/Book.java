package com.nagarro.model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Table(name = "Book")
@Entity
public class Book {
	@Id
	@Column
	private String bookCode;
	
	@Column
	private String bookTitle;
	
	@Column
	private String addedOn;
	
	@OneToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	

}
