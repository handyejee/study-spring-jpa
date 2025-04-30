package com.spring.study.springjpabook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, name = "book_name")
  private String bookName; // 책이름

  @Column(nullable = false)
  private String author; // 저자

  // 생성자
  public Book(String bookName, String author){
    this.bookName = bookName;
    this.author = author;
  }

  // setter 대신
  public void update(String bookName, String author) {
    this.bookName = bookName;
    this.author = author;
  }
}
