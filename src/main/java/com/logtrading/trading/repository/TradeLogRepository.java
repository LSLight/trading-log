package com.logtrading.trading.repository;

import com.logtrading.trading.domain.TradeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeLogRepository extends JpaRepository<TradeLog, Long> {

    // Query Method 추가
    // 종목 이름(StockName)으로 리스트를 찾기
    // 스프링이 알아서 기능을 생성
    List<TradeLog> findByStockName(String stockName);
}