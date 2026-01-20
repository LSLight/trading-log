package com.logtrading.trading.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
public class TradeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;      // 날짜

    private String type;         // 매수/매도

    private String stockName;    // 종목명

    @NotNull(message = "가격을 입력해주세요.")
    @Positive(message = "가격은 0보다 커야 합니다.")
    private Double price;        // 가격

    @NotNull(message = "수량을 입력해주세요.")
    @Positive(message = "수량은 0보다 커야 합니다.")
    private Double quantity;     // 수량

    private String memo;         // 메모

    public TradeLog() {

    }

    public TradeLog(LocalDate date, String type, String stockName, Double price, Double quantity, String memo) {
        this.date = date;
        this.type = type;
        this.stockName = stockName;
        this.price = price;
        this.quantity = quantity;
        this.memo = memo;
    }
}
