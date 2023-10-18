package com.nagarro.model;

public class AuthorDTO {
	private Integer authorId;
	private String authorName;
	public AuthorDTO(Integer authorId) {
		this.authorId = authorId;
	}
	public AuthorDTO() {
		super();
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
