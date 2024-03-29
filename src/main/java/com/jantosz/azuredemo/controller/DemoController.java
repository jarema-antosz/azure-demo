package com.jantosz.azuredemo.controller;

import com.jantosz.azuredemo.entity.Book;
import com.jantosz.azuredemo.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Value("${firstSecret}")
    private String firstSecret;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String root() {
        LOGGER.warn("accessing demo controller / path");
        return "Hello from demo app root";
    }

    @GetMapping("/demo")
    public String demo() {
        LOGGER.warn("accessing demo controller /demo path");
        return "Hello from demo app";
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {

        LOGGER.warn("accessing demo controller /books path");
        return bookRepository.findAll();
    }

    @GetMapping("/testSecret")
    public String testSecret() {

        LOGGER.warn("accessing demo controller /testSecret path");
        return this.firstSecret;
    }

}
