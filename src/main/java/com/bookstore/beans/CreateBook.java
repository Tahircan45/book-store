package com.bookstore.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bookstore.entities.Author;
import com.bookstore.entities.Book;
import com.bookstore.entities.Genre;
import com.bookstore.service.AuthorService;
import com.bookstore.service.BookService;
import com.bookstore.util.NumberGenerator;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class CreateBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookService bookService;

	@EJB
	private AuthorService authorService;
	
	@Inject
	private NumberGenerator numberGenerator;
	
	@Setter
	@Getter
	private Book newBook=new Book();
	
	@Setter
	@Getter
	private List<Author> authors;
	
	@PostConstruct
	public void init() {
		authors= authorService.getAllAuthors();
	}
	
	public String saveBook() {
		newBook.setIsbn(numberGenerator.generateNumber());
		bookService.createBook(newBook);
		return "index.xhtml?faces-redirect=true";
	}
	public Genre[] getGenres() {
		return Genre.values();
	}
	
}
