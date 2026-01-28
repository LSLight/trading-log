package com.logtrading.trading.dto;

import com.logtrading.trading.domain.TradeType;
import lombok.Data;

@Data
public class TradingRecordDto {
    private String date;      // 화면에서 "2026-01-28" 문자로 옴
    private TradeType type;   // BUY 또는 SELL
    private Double price;     // 가격
    private Integer quantity; // 수량
    private String memo;      // 메모
}