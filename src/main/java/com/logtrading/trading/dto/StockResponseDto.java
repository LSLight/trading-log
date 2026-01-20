package com.logtrading.trading.dto;

import com.logtrading.trading.domain.Stock;
import lombok.Getter;
import lombok.Setter;
import com.logtrading.trading.domain.TradeLog; // 추가
import java.util.List; // 추가

//dto sql
@Getter @Setter
public class StockResponseDto {
    // 1. 기본 정보 (Stock에서 가져옴)
    private Long id;
    private String stockName;
    private String code;
    private String logoUrl;
    private String buyStrategy;
    private String sellStrategy;
    private String strategyTags;

    // 2. 계산된 정보
    private Double totalQuantity; // 보유 수량
    private Double avgPrice;      // 평단가
    private Double currentValuation; // 평가 금액 (현재가 * 수량) - 현재가는 나중에 API로 연동
    private Double investedAmount; // 총 투자 원금

    private List<TradeLog> tradeLogs;

    // 생성자: Stock 엔티티를 받아서 DTO로 변환
    public StockResponseDto(Stock stock) {
        this.id = stock.getId();
        this.stockName = stock.getStockName();
        this.code = stock.getCode();
        this.logoUrl = stock.getLogoUrl();
        this.buyStrategy = stock.getBuyStrategy();
        this.sellStrategy = stock.getSellStrategy();
        this.strategyTags = stock.getStrategyTags();

        // 초기값은 0으로 세팅
        this.totalQuantity = 0.0;
        this.avgPrice = 0.0;
        this.investedAmount = 0.0;
    }
}