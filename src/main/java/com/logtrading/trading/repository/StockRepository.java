package com.logtrading.trading.repository;

import com.logtrading.trading.domain.MarketType;
import com.logtrading.trading.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    // 타입 (국장/미장)으로 찾기
    List<Stock> findByMarketType(MarketType marketType);
}