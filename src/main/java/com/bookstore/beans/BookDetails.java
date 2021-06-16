package com.bookstore.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.bookstore.entities.Book;
import com.bookstore.service.BookService;

@Named
@RequestScoped
public class BookDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookService bookService;
	
	private int bookId;
	private Book book;
	
	public void onLoad() {
		 book=bookService.findBook(bookId);
	}

	public int getBookId() {
		return bookId;
	}

	public Book getBook() {
		return book;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
