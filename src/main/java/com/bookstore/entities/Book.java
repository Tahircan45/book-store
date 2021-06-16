package com.bookstore.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="book")
public class Book {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@Size(min = 1, max = 30, message = "En 1 En Fazla 30 Karakter giriniz!")
	private String name;
	
	private double price;
	
	private String isbn;
	
	private Genre genre;
	
	@ManyToOne(cascade = CascadeType.ALL,
				fetch = FetchType.LAZY)
	private Author author;
	
	public Book(String name, double price, String isbn, Genre genre, Author author) {
		this.name = name;
		this.price = price;
		this.isbn = isbn;
		this.genre=genre;
		this.author=author;
	}
	
}

