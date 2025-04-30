package com.spring.study.springjpabook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "book_id", nullable = false)
 private Book book;

 @Column(nullable = false)
 private String title;

 @Column(nullable = false)
 private String content;

 public Review(Book book, String title, String content) {
  this.book = book;
  this.title = title;
  this.content = content;
 }

 public void update(String title, String content) {
  this.title = title;
  this.content = content;
 }

 public static Review of(Book book, String title, String content) {
  return new Review(book, title, content);
 }
}
