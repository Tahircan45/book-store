package com.bookstore.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.bookstore.entities.Author;

@Stateless
@Transactional
public class AuthorServiceImpl implements AuthorService{
	 private static EntityManagerFactory emf= Persistence
	            .createEntityManagerFactory("BookStore");


	    private static EntityManager em=emf.createEntityManager();

	@Override
	public Author createAuthor(Author author) {
		em.persist(author);
		return author;
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors=em.createQuery("select a from Author a",Author.class).getResultList();
		return authors;
	}
}
