package com.tromaya.simple_spring_boot_template;

import com.tromaya.simple_spring_boot_template.domain.entities.Author;
import com.tromaya.simple_spring_boot_template.domain.entities.Authority;
import com.tromaya.simple_spring_boot_template.domain.entities.Book;
import com.tromaya.simple_spring_boot_template.domain.entities.User;
import com.tromaya.simple_spring_boot_template.repositories.AuthorRepository;
import com.tromaya.simple_spring_boot_template.repositories.BookRepository;
import com.tromaya.simple_spring_boot_template.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SimpleSpringBootTemplateApplication {

	public static void main(String[] args) {

        SpringApplication.run(SimpleSpringBootTemplateApplication.class, args);
	}

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initData() {

        // Check if data already exists
        if (authorRepository.count() > 0) {
            return;
        }

        // Create 3 dummy Authors
        Author author1 = Author.builder()
                .name("George")
                .lastName("Orwell")
                .biography("English novelist and essayist")
                .nationality("British")
                .birthYear(1903)
                .deathYear(1950)
                .writtenBooks(new ArrayList<>())
                .build();

        Author author2 = Author.builder()
                .name("Jane")
                .lastName("Austen")
                .biography("English novelist")
                .nationality("British")
                .birthYear(1775)
                .deathYear(1817)
                .writtenBooks(new ArrayList<>())
                .build();

        Author author3 = Author.builder()
                .name("Mark")
                .lastName("Twain")
                .biography("American writer and humorist")
                .nationality("American")
                .birthYear(1835)
                .deathYear(1910)
                .writtenBooks(new ArrayList<>())
                .build();

        // Save authors to the database
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        System.out.println("Sample authors created and saved successfully!");
        System.out.println("Authors: " + author1.getName() + ", " + author2.getName() + ", " + author3.getName());

        // Create 3 dummy Books
        Book book1 = Book.builder()
                .title("1984")
                .isbn("978-0-452-28423-4")
                .description("Dystopian social science fiction novel")
                .author(author1)
                .publishedYear(1949)
                .language("English")
                .price(12.99)
                .pages(328)
                .rating(4.5)
                .leftInStock(50)
                .build();

        Book book2 = Book.builder()
                .title("Pride and Prejudice")
                .isbn("978-0-14-143951-8")
                .description("Romantic novel")
                .author(author2)
                .publishedYear(1813)
                .language("English")
                .price(10.99)
                .pages(432)
                .rating(4.3)
                .leftInStock(30)
                .build();

        Book book3 = Book.builder()
                .title("The Adventures of Tom Sawyer")
                .isbn("978-0-486-40077-6")
                .description("Adventure novel")
                .author(author3)
                .publishedYear(1876)
                .language("English")
                .price(8.99)
                .pages(274)
                .rating(4.1)
                .leftInStock(25)
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        System.out.println("Sample books created and saved successfully!");
        System.out.println("Books: " + book1.getTitle() + ", " + book2.getTitle() + ", " + book3.getTitle());

        // Create 3 dummy Users
        User user1 = User.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .role("USER")
                .borrowedBooks(new ArrayList<>())
                .authorities(List.of(new Authority("ROLE_USER")))
                .build();

        User user2 = User.builder()
                .name("Jane")
                .lastName("Smith")
                .email("jane.smith@example.com")
                .password("password456")
                .role("ADMIN")
                .borrowedBooks(new ArrayList<>())
                .authorities(List.of(new Authority("ROLE_ADMIN")))
                .build();

        User user3 = User.builder()
                .name("Bob")
                .lastName("Johnson")
                .email("bob.johnson@example.com")
                .password("password789")
                .role("USER")
                .borrowedBooks(new ArrayList<>())
                .authorities(List.of(new Authority("ROLE_USER")))
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        System.out.println("Sample users created and saved successfully!");
        System.out.println("Users: " + user1.getName() + ", " + user2.getName() + ", " + user3.getName());
    }

}
