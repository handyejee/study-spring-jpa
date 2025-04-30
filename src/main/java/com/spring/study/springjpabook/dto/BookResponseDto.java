package com.spring.study.springjpabook.dto;

import com.spring.study.springjpabook.entity.Book;
import lombok.Getter;

@Getter
public class BookResponseDto {
  private final Long id;
  private final String bookName;
  private final String author;

  // 생성자
  public BookResponseDto(Long id, String title, String author) {
    this.id = id;
    this.bookName = title;
    this.author = author;
  }

  // 엔티티 dto로 변환하는 팩토리메서드
  public static BookResponseDto of(Book book) {
    return new BookResponseDto(
        book.getId(),
        book.getBookName(),
        book.getAuthor()
    );
  }
}
