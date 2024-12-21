package com.abc.bank.mortgage.bookshop.repository;

import com.abc.bank.mortgage.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
