package com.spring.study.springjpabook.service;

import com.spring.study.springjpabook.dto.ReviewRequestDto;
import com.spring.study.springjpabook.dto.ReviewResponseDto;
import com.spring.study.springjpabook.entity.Book;
import com.spring.study.springjpabook.entity.Review;
import com.spring.study.springjpabook.repository.BookRepository;
import com.spring.study.springjpabook.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final BookRepository bookRepository;

  @Transactional
  public ReviewResponseDto save(ReviewRequestDto reviewRequestDto) {
    Book book = bookRepository.findById(reviewRequestDto.getBookId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found. id=" + reviewRequestDto.getBookId()));

    Review saved = reviewRepository.save(reviewRequestDto.toEntity(book));
    return ReviewResponseDto.from(saved);
  }

  @Transactional
  public void update(Long id, ReviewRequestDto reviewRequestDto) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Review not found. id=" + id));
    review.update(reviewRequestDto.getTitle(), reviewRequestDto.getContent()); // 변경감지로 save 없이도 동작
  }

  public void deleteById(Long id) {
    reviewRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public ReviewResponseDto findById(Long id) {
    Review review = reviewRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Review not found. id=" + id));
    return ReviewResponseDto.from(review);
  }

  // 책을 기준으로 리뷰 목록을 조회하는 메서드
  @Transactional(readOnly = true)
  public List<ReviewResponseDto> findAllByBookId(Long bookId) {
    List<Review> reviews = reviewRepository.findAllByBookId(bookId);
    return reviews.stream()
        .map(ReviewResponseDto::from)
        .toList();
  }
}
