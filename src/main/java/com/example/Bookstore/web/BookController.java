package com.example.Bookstore.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@Controller
public class BookController {

	@Bean
	public CommandLineRunner demo(
			BookRepository bookRepository, 
			CategoryRepository categoryRepository,
			UserRepository urepository) {
		return (args) -> {

			User user1 = new User(
					"michel", 
					"$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", 
					"user@email.com",
					"USER");
			User user2 = new User(
					"admin", 
					"$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", 
					"admin@email.com",
					"ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			Category c1 = new Category("Roman");
			categoryRepository.save(c1);

			Category c2 = new Category("Fiction");
			categoryRepository.save(c2);

			Category c3 = new Category("Science-fiction");
			categoryRepository.save(c3);

			Category c4 = new Category("Medieval fantastique");
			categoryRepository.save(c4);

			Category c5 = new Category("Novel");
			categoryRepository.save(c5);

			List<Book> books = new ArrayList();

			books.add(new Book("Harry Pother", "JKR", "01-123", 1990, 21.2, c1));
			books.add(new Book("Lord of the rings", "Tolkien", "02-124", 1970, 99.99, c4));
			books.add(new Book("Database Management seconde edition", "U&N known", "03-123", 2021, 5, c2));
			books.add(new Book("L'illiade", "Achille Talon", "AB-123", 1570, 3.30, c5));
			books.add(new Book("L'odysee", "Troy Mac Lure", "C3-123", 1580, 4.2, c3));

			bookRepository.saveAll(books);
		};
	}

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/login")
	public String login(Model model) {

		return "login";
	}

	@GetMapping("/index")
	public String index(Model model) {

		return "index";
	}

	@GetMapping("/booklist")
	public String booklist(Model model) {

		Iterable<Book> books = bookRepository.findAll();

		model.addAttribute("books", books);

		return "booklist";
	}

	@GetMapping("/delete/{id}")

	public String deleteBook(@PathVariable("id") Long bookId, Model model) {

		bookRepository.deleteById(bookId);

		return "redirect:../booklist";
	}

	@GetMapping("/add")
	public String addBook(Model model) {

		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());

		return "addbook";
	}

	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {

		Optional<Book> book = bookRepository.findById(bookId);

		if (book.isEmpty())
			return "redirect:../booklist";

		model.addAttribute("book", book.get());
		model.addAttribute("categories", categoryRepository.findAll());

		return "editbook";
	}

	@PostMapping("/save")
	public String saveBook(Book book) {

		bookRepository.save(book);

		return "redirect:booklist";
	}
}
