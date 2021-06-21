package com.bookstore.util;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import com.bookstore.entities.Book;

public class LazySorter implements Comparator<Book> {

	private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Book book1, Book book2) {
        try {
            Object value1 = book1.getClass().getField(sortField).get(book1);
            Object value2 = book2.getClass().getField(sortField).get(book2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
