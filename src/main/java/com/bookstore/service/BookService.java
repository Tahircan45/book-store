package com.bookstore.service;

import java.util.List;

import com.bookstore.entities.Author;
import com.bookstore.entities.Book;

public interface BookService {
	Book createBook(Book book);
	Book findBook(int id);
	List<Book> getAllBook();
	List<Author> getAllAuthors();
	Author findAuthor(int id);
	Author createAuthor(Author author);
	List<Book> getPagedBook(int pageNum, int pageSize);
}
