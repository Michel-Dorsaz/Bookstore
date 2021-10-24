package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;

	@Test
	public void findAllCategoryShouldReturn5() {
		
		Category c1 = new Category("Roman");
		repository.save(c1);

		Category c2 = new Category("Fiction");
		repository.save(c2);

		Category c3 = new Category("Science-fiction");
		repository.save(c3);

		Category c4 = new Category("Medieval fantastique");
		repository.save(c4);

		Category c5 = new Category("Novel");
		repository.save(c5);
		
		Iterable<Category> categories = repository.findAll();
		
	
		assertThat(categories).hasSize(5);

	}


	@Test
	public void deleteCategory() {

		Category c1 = new Category("Roman");
		repository.save(c1);
		
		Category category = repository.findById((long) 1).get();

		assertThat(category.getId()).isNotNull();

		repository.deleteById((long) 1);

		Optional<Category> deletedcategory = repository.findById((long) 1);

		assertThat(deletedcategory.isEmpty()).isTrue();

	}

	@Test
	 public void editCategory() {
		
		Category category = new Category("Category");
		 
		repository.save(category);
		 
		assertThat(category.getId()).isNotNull();
		assertThat(category.getName()).contains("Category");
		 
		category.setName("newName");
		 
		repository.save(category);
		 
		assertThat(category.getId()).isNotNull();
		assertThat(category.getName()).contains("newName");
	}
}
