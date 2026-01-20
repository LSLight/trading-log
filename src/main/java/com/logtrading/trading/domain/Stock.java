package com.logtrading.trading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 종목명
    // 예: 테슬라, 엔비디아, 삼성전자
    private String stockName;

    private String code;      // 예: TSLA, 005930
    private String logoUrl;   // 예: https://logo.clearbit.com/tesla.com

    // 매수 전략 (긴 글)
    // 예: "RSI 30-40에 매수 | 볼밴하단 터치"
    @Column(columnDefinition = "TEXT")
    private String buyStrategy;

    // 매도 전략 (긴 글)
    // 예: "RSI 70이상 | 전고점 부근"
    @Column(columnDefinition = "TEXT")
    private String sellStrategy;

    // 태그
    // 예: "RSI, 볼린저밴드, 20일선"
    private String strategyTags;

    public Stock() {
    }

    public Stock(String stockName, String code, String logoUrl, String buyStrategy, String sellStrategy, String strategyTags) {
        this.stockName = stockName;
        this.code = code;
        this.logoUrl = logoUrl;
        this.buyStrategy = buyStrategy;
        this.sellStrategy = sellStrategy;
        this.strategyTags = strategyTags;
    }
}