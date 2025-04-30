package com.spring.study.springjpabook.repository;

import com.spring.study.springjpabook.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  /**
   * 조회한 책에 있는 모든 리뷰 목록을 조회합니다.
   * @param bookId 책 고유번호
   * @return 책에 대한 리뷰 목록
   */
  List<Review> findAllByBookId(Long bookId);
}
