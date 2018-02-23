package com.ddframe.tools;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Streams {
	public static void main(String[] args) {
		List<Book> books = getBooks();
		List<String> bookNames = books.stream().filter(book -> book.getIsbn().endsWith("0")).map(Book::getTitle).collect(Collectors.toList());
		bookNames.stream().limit(10).skip(2).forEach(System.out::println);
	}

	private static List<Book> getBooks() {
		List<Book> books = Lists.newArrayList();
		for (int i = 0; i < 1000; i++) {
			books.add(new Book(i));
		}
		return books;
	}
}
