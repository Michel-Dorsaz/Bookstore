package com.example.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@RestController
public class BookRestController {

	@Autowired
	private BookRepository bookRepository;
	

	@GetMapping("/books")
	public @ResponseBody List<Book> getBooklist() {
		
		return (List<Book>) bookRepository.findAll();
		
	}
	
	@GetMapping("/book/{id}")
	public @ResponseBody Book getBookbyId(@PathVariable("id") Long bookId) {
		
		if(bookRepository.findById(bookId).isEmpty()) {
			
			return null;
		}
		
		return bookRepository.findById(bookId).get();
		
	}
	
	
	
}
