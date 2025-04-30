package com.spring.study.springjpabook.service;

import com.spring.study.springjpabook.dto.BookRequestDto;
import com.spring.study.springjpabook.dto.BookResponseDto;
import com.spring.study.springjpabook.entity.Book;
import com.spring.study.springjpabook.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  // 책 저장
  @Transactional
  public BookResponseDto save(BookRequestDto requestDto) {
    Book book = new Book(requestDto.getBookName(), requestDto.getAuthor());
    Book saved = bookRepository.save(book);
    return BookResponseDto.of(saved);
  }

  // 책 수정
  @Transactional
  public BookResponseDto update(Long id, BookRequestDto requestDto) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다. id=" + id));
    book.update(requestDto.getBookName(), requestDto.getAuthor());
    return BookResponseDto.of(book);
  }

  // 책 삭제
  @Transactional
  public void deleteById(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다. id=" + id));

    bookRepository.deleteById(id);
  }

  // 책 단건 조회
  @Transactional(readOnly = true)
  public BookResponseDto findById(Long id) {

    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 책입니다. id=" + id));
    return BookResponseDto.of(book); // Book -> BookResponseDto 변환
  }

  // 책 전체조회
  @Transactional(readOnly = true)
  public List<BookResponseDto> findAll() {
    return bookRepository.findAll().stream()
        .map(BookResponseDto::of)
        .collect(Collectors.toList());
  }
}
