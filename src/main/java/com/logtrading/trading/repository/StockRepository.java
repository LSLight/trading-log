package com.logtrading.trading.repository;

import com.logtrading.trading.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    // empty 가능
    // JpaRepository 알아서 '저장', '조회', '수정', '삭제' 구현O

    // 검색 기능 추가
    // Containing: 해당 글자가 포함되면 다 찾음 (예: "테" -> 테슬라, 테팔)
    // IgnoreCase: 대소문자 무시 (tesla == TESLA)
    List<Stock> findByStockNameContainingIgnoreCase(String keyword);

    // 코드로도 찾기(예: TSLA 검색)
    List<Stock> findByCodeContainingIgnoreCase(String keyword);
}