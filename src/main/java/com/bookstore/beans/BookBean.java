package com.bookstore.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.bookstore.entities.Book;
import com.bookstore.service.BookService;

@Named
@SessionScoped
public class BookBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookService bookService;
	
	private List<Book> bookList;

	public List<Book> getBookList() {
		bookList=bookService.getAllBook();
		return bookList;
	}
}
