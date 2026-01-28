package com.logtrading.trading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class TradingRecord {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime tradingDate; // 거래 일시

    @Enumerated(EnumType.STRING)
    private TradeType type; // BUY(매수), SELL(매도)

    private Double price;  // 거래 가격
    private Integer quantity; // 거래 수량
    private String memo;   // 메모

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock; // 어떤 종목의 기록인지 연결

    // 생성자
    public TradingRecord(LocalDateTime tradingDate, TradeType type, Double price, Integer quantity, String memo) {
        this.tradingDate = tradingDate;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.memo = memo;
    }
}