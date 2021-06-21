package com.webserivce.restfull;

import com.webserivce.restfull.resources.Book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfullApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// use for print data to cmd
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return argv -> {
			System.out.println("=============Data from GET /getbook==============");
			// connect to GET the Book
			Book book = restTemplate.getForObject("http://localhost:8080/getbook", Book.class);
			System.out.println(book);

			// modify the book and POST
			book.getAuthor().setName("Some random author");
			restTemplate.postForObject("http://localhost:8080/addbook", book, Book.class);
		};
	}
}
