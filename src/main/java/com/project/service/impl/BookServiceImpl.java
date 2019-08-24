package com.project.service.impl;

import com.project.entity.Book;
import com.project.jpa.JpaUtil;
import com.project.service.BookService;

import javax.persistence.EntityManager;
import java.util.List;

public class BookServiceImpl implements BookService {

    private EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Book findOneById(Long id) {

        Book book = entityManager.find(Book.class, id);
        entityManager.detach(book);
        return book;
    }

    @Override
    public List<Book> findAll() {

        List<Book> books = entityManager.createQuery("SELECT b FROM Book b").getResultList();
        entityManager.detach(books);
        return books;
    }
}
