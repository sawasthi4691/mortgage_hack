package com.abc.bank.mortgage.bookshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idbook", nullable = false)
    private Integer idbook;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "price")
    private String price;

    @Column(name = "edition")
    private String edition;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
