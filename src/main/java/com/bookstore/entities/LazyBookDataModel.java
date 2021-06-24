package com.bookstore.entities;

import java.io.Serializable;
import java.util.Collection;
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
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

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
    public Book getRowData(String rowKey) {
        for (Book customer : books) {
            if (customer.getId() == Integer.parseInt(rowKey)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Book customer) {
        return String.valueOf(customer.getId());
    }

	@Override
	public List<Book> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        long rowCount = books.stream()
        		.filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .count();
//		List<Book> list= bookService.getPagedBook(first, pageSize);
		
        List<Book> newBooks = books.stream()
        		.skip(first)
        		.filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
        		.limit(pageSize)
        		.collect(Collectors.toList());

//        if(!sortBy.isEmpty()) {
//			List<Comparator<Book>> comparators=sortBy.values().stream()
//					.map(o->new LazySorter(o.getField(), o.getOrder()))
//					.collect(Collectors.toList());
//			Comparator<Book> cp=ComparatorUtils.chainedComparator(comparators);
//			newBooks.sort(cp);
//		}

		setRowCount((int)rowCount);		
		return newBooks;
	}
	private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(o.getClass().getField(filter.getField()).get(o));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            } catch (ReflectiveOperationException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }

        return matching;
    }

}
