package com.project.service;

import com.project.entity.Book;

import java.util.List;

public interface BookService {

    Book findOneById(Long id);

    List<Book> findAll();

}
