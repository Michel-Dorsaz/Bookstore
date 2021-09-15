package com.example.Bookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			
			List<Book> books = new ArrayList();
			
			books.add(new Book("Harry Pother", "JKR", "01-123", 1990, 21.2));
			books.add(new Book("Lord of the rings", "Tolkien", "02-124", 1970, 99.99));
			books.add(new Book("Database Management seconde edition", "U&N known", "03-123", 2021, 5));
			books.add(new Book("L'illiade", "Achille Talon", "AB-123", 1570, 3.30));
			books.add(new Book("L'odysee", "Troy Mac Lure", "C3-123", 1580, 4.2));
			
			repository.saveAll(books);
		};
	}
	
	
	
	
	
	@Autowired
	private BookRepository repository;

	
	@GetMapping("/index")
	public String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/booklist")
	public String booklist(Model model) {
		
		Iterable<Book> books = repository.findAll();
		
		model.addAttribute("books", books);
		
		return "booklist";
	}
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		
		repository.deleteById(bookId);
		
		return "redirect:../booklist";
	}
	
	@GetMapping("/add")
	public String addBook(Model model) {
		
		model.addAttribute("book", new Book());
		
		return "addbook";
	}
	
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		
		//NoSuchElement possible ?! should ensure safety
		Book book = repository.findById(bookId).get();
		
		model.addAttribute("book", book);
		
		return "editbook";
	}
	
	
	@PostMapping("/save")
	public String saveBook(Book book) {	
	
		repository.save(book);
		
		return "redirect:booklist";
	}
}
