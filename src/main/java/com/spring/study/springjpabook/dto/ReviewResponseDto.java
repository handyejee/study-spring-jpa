package com.spring.study.springjpabook.dto;

import com.spring.study.springjpabook.entity.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {
  private final Long id;
  private final String title;
  private final String content;

  /* final 필드인 경우 생성자에서 초기화 필요
     생성자 통해 객체로 만들어주는 순간 필수 값을 넣어줘야 하기 때문에 안전하다.
  */
  public ReviewResponseDto(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public static ReviewResponseDto from(Review review) {
    return new ReviewResponseDto(
        review.getId(),
        review.getTitle(),
        review.getContent()
    );
  }
}
