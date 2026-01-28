package com.logtrading.trading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;
    private String code;

    @Enumerated(EnumType.STRING)
    private MarketType marketType;

    // ⭐ 태그 (기존과 동일)
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    // ⭐ 매수 전략 목록(List)으로 관리
    @ElementCollection
    private List<String> buyStrategies = new ArrayList<>();

    // ⭐매도 전략 목록(List)으로 관리
    @ElementCollection
    private List<String> sellStrategies = new ArrayList<>();

    // 생성자 (테스트 데이터용)
    public Stock(String stockName, String code, MarketType marketType, List<String> tags, List<String> buyStrategies, List<String> sellStrategies) {
        this.stockName = stockName;
        this.code = code;
        this.marketType = marketType;
        this.tags = tags;
        this.buyStrategies = buyStrategies;
        this.sellStrategies = sellStrategies;
    }
    // 1:N
    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TradingRecord> records = new ArrayList<>();

    // 편의 메서드 (기록 추가 시 종목과 서로 연결)
    public void addRecord(TradingRecord record) {
        this.records.add(record);
        record.setStock(this);
    }
}