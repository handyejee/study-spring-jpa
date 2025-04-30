package com.spring.study.springjpabook.dto;

import com.spring.study.springjpabook.entity.Book;
import com.spring.study.springjpabook.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
  private Long bookId;
  private String title;
  private String content;

  public Review toEntity(Book book) {
    return Review.of(book, title, content);
  }
}
