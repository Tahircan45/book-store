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
import com.bookstore.service.BookService;
import com.bookstore.util.NumberGenerator;

import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class CreateBook implements Serializable {

	/**
	 * 
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookService bookService;

	
	@Inject
	private NumberGenerator numberGenerator;
	
	@Setter
	@Getter
	private Book newBook=new Book();
	
	@Setter
	@Getter
	private int authorId;
	
	@Setter
	@Getter
	private String authorName;
	
	@Setter
	@Getter
	private boolean newAuthor;
	
	@Setter
	@Getter
	private List<Author> authors;
	
	@PostConstruct
	public void init() {
		authors= bookService.getAllAuthors();
	}
	
	public String saveBook() {		
		newBook.setIsbn(numberGenerator.generateNumber());
		
		if(newAuthor) {
			Author newAuthor=new Author(authorName);
			bookService.createAuthor(newAuthor);
			newBook.setAuthor(newAuthor);			
		}
		
		else {
			newBook.setAuthor(bookService.findAuthor(authorId));
		}
		
		bookService.createBook(newBook);
		return "index.xhtml?faces-redirect=true";
	}
	public Genre[] getGenres() {
		return Genre.values();
	}
}
