package com.bookstore.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.bookstore.entities.Author;
import com.bookstore.entities.Book;
import com.bookstore.entities.Genre;
import com.bookstore.util.NumberGenerator;

@Stateless
@Transactional
public class BookServiceImpl implements BookService{

	 private static EntityManagerFactory emf= Persistence
	            .createEntityManagerFactory("BookStore");


	private static EntityManager em=emf.createEntityManager();
	
	@Inject
	private NumberGenerator generator;
	
	@PostConstruct
	private void init() {
		Author frank=new Author("Frank Herbert");
		createBook(new Book("Dune",8.5,generator.generateNumber(),Genre.ScienceFiction,frank));
		createBook(new Book("Dune Mesihi",15.5,generator.generateNumber(),Genre.ScienceFiction,frank));
		
		Author Zweig=new Author("Stefan Zweig");
		createBook(new Book("Satranç",4.20,generator.generateNumber(),Genre.Classic,Zweig));
		
		Author tolkien=new Author("J.R.R. Tolkein");
		createBook(new Book("Hobbit",45.15,generator.generateNumber(),Genre.Fantasy,tolkien));

	}
	
    @Override
    public Book createBook(Book book){
        try{
            em.persist(book);
            return book;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Book findBook(int id){
        Book book=em.find(Book.class, id);
        if(book!=null){
            return book;
        }

        else{
            return null;
        }
    }
    
    @Override
    public List<Book> getAllBook(){
        List<Book> bookList=em.createQuery("select b from Book b",Book.class).getResultList();
        return bookList;
    }
	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors=em.createQuery("select a from Author a",Author.class).getResultList();
		System.out.println(authors);
		return authors;
	}

	@Override
	public Author findAuthor(int id) {		
		return em.find(Author.class,id);
	}
	
	@Override
	public Author createAuthor(Author author) {
		em.persist(author);
		return author;
	}
	@Override
	public List<Book> getPagedBook(int pageNum,int pageSize){
		TypedQuery<Book> query=em.createQuery("select b from Book b",Book.class);
		
		query.setFirstResult(pageNum*pageSize);
		query.setMaxResults(pageSize);
		List <Book>
 		list= query.getResultList();
		return list;
	}
}
