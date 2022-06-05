package com.jantosz.azuredemo.repository;

import com.jantosz.azuredemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}