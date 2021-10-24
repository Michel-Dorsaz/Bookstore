package com.example.Bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void findAllShouldReturn5() {
		
		Category c1 = new Category("Roman");
		categoryRepository.save(c1);
		
		List<Book> books = new ArrayList();

		books.add(new Book("Harry Pother", "JKR", "01-123", 1990, 21.2, c1));
		books.add(new Book("Lord of the rings", "Tolkien", "02-124", 1970, 99.99, c1));
		books.add(new Book("Database Management seconde edition", "U&N known", "03-123", 2021, 5, c1));
		books.add(new Book("L'illiade", "Achille Talon", "AB-123", 1570, 3.30, c1));
		books.add(new Book("L'odysee", "Troy Mac Lure", "C3-123", 1580, 4.2, c1));

		repository.saveAll(books);
		
		Iterable<Book> booklist = repository.findAll();
		assertThat(booklist).hasSize(5);

	}

	@Test
	 public void addBook() {
		
	 Book book = new Book(
			 "name", 
			 "author", 
			 "ISBN", 
			 2021, 
			 99.99, 
			 new Category());	 
	 
	 repository.save(book);
	 
	 assertThat(book.getId()).isEqualTo(7);
	 assertThat(book.getAuthor()).contains("author");
	 
	 }
	
	@Test
	 public void deleteBook() {
		
		 Book newbook = new Book(
				 "name", 
				 "author", 
				 "ISBN", 
				 2021, 
				 99.99, 
				 new Category());	 
		 
		 repository.save(newbook);
		 
		Book book = repository.findById(newbook.getId()).get();
		
		assertThat(book.getId()).isNotNull();
		
		repository.deleteById(newbook.getId());
		
		Optional<Book> deletedbook = repository.findById((long) 1);
		
		assertThat(deletedbook.isEmpty()).isTrue();
	
	}
	
	@Test
	 public void editBook() {
		
		 Book book = new Book(
				 "name", 
				 "author", 
				 "ISBN", 
				 2021, 
				 99.99, 
				 new Category());
		 
		 repository.save(book);
		 
		 assertThat(book.getId()).isNotNull();
		 assertThat(book.getAuthor()).contains("author");
		 
		 book.setTitle("newTitle");
		 book.setAuthor("newAuthor");
		 book.setPrice(0);
		 
		 repository.save(book);
		 
		 assertThat(book.getId()).isNotNull();
		 assertThat(book.getAuthor()).contains("newAuthor");
		 assertThat(book.getTitle()).contains("newTitle");
		 assertThat(book.getPrice()).isEqualTo(0);

	}
	
	
	
}
