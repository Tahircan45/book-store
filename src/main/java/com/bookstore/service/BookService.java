package com.bookstore.service;

import java.util.List;

import com.bookstore.entities.Book;

public interface BookService {
	Book createBook(Book book);
	Book findBook(int id);
	List<Book> getAllBook();
}
