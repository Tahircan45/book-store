package com.bookstore.service;

import java.util.List;

import com.bookstore.entities.Author;

public interface AuthorService {
	Author createAuthor(Author author);
	List<Author> getAllAuthors();
}
