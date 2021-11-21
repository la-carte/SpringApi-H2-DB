package com.example.springdata.dao;


import com.example.springdata.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookDAO extends CrudRepository<Book,Integer> {

    List<Book> findAll() ;
    Book findByTitle(String title);
    Book findById(int id);
    List<Book> findAllByAuthor(String author);
    void deleteById(int id);
}
