package com.logtrading.trading.repository;

import com.logtrading.trading.domain.TradingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingRecordRepository extends JpaRepository<TradingRecord, Long> {
}