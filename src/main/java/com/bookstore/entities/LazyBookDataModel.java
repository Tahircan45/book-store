package com.bookstore.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.ComparatorUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import com.bookstore.service.BookService;
import com.bookstore.util.LazySorter;


public class LazyBookDataModel extends LazyDataModel<Book>{
//	private BookService bookService;
//	
//	public LazyBookDataModel(BookService bookService) {
//		this.bookService=bookService;
//	}
	
	private List<Book> books;
	public LazyBookDataModel(List<Book> books ) {
		this.books=books;
	}
	
	@Override
	public List<Book> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        long rowCount = books.stream()
                .count();
//		List<Book> list= bookService.getPagedBook(first, pageSize);
		
        List<Book> newBooks = books.stream()
        		.skip(first)
        		.limit(pageSize)
        		.collect(Collectors.toList());

        if(!sortBy.isEmpty()) {
			List<Comparator<Book>> comparators=sortBy.values().stream()
					.map(o->new LazySorter(o.getField(), o.getOrder()))
					.collect(Collectors.toList());
			Comparator<Book> cp=ComparatorUtils.chainedComparator(comparators);
			newBooks.sort(cp);
		}

		setRowCount((int)rowCount);		
		return newBooks;
	}

}
