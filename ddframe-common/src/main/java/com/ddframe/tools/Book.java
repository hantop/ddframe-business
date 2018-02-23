package com.ddframe.tools;

public class Book {
	private String isbn;
	private String title;

	public Book(int i) {
		this.isbn = String.valueOf(i);
		this.title = "title" + i;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
