package com.bookstore.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import com.bookstore.entities.Book;
import com.bookstore.entities.Genre;
import com.bookstore.service.BookService;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class BookBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BookService bookService;
	
	@Setter
	@Getter
	private List<Book> bookList;

	@Setter
	@Getter
	private List<Book> filteredBookList;
	
	@Setter
	@Getter
	private List<FilterMeta> filterBy;
	
	@PostConstruct
	public void init() {
		bookList=bookService.getAllBook();
	}
	
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        double filterDouble=getDouble(filterText);
        
        Book book=(Book)value;
        
        return book.getName().toLowerCase().contains(filterText)
        		||book.getIsbn().toLowerCase().contains(filterText)
        		||book.getGenre().toString().contains(filterText)
        		||book.getPrice() < filterDouble;        		
    }

    private double getDouble(String string) {
        try {
            return Double.parseDouble(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
	public Genre[] getGenres() {
		return Genre.values();
	}
}
