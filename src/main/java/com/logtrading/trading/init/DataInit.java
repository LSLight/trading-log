package com.logtrading.trading.init;

import com.logtrading.trading.domain.MarketType;
import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.domain.TradeType;
import com.logtrading.trading.domain.TradingRecord;
import com.logtrading.trading.service.StockService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final StockService stockService;

    @PostConstruct
    public void init() {
        stockService.deleteAll(); // 기존 데이터 싹 비우고 시작

        // ==========================================
        // 1. 테슬라 (미장) - 매매 기록 포함
        // ==========================================
        Stock tesla = new Stock(
                "테슬라", "TSLA", MarketType.OVERSEAS,
                List.of("RSI|purple", "볼린저밴드|yellow", "20일선|green"),
                List.of("RSI 30-40에 매수", "볼밴하단 터치+양봉", "$424-430 부근"),
                List.of("RSI 70이상", "볼밴상단 터치", "20일선 뚫고 내려갈 때")
        );

        // ➕ 매매 일지 추가 (addRecord 메서드 사용)
        // 1) 매수 기록
        tesla.addRecord(new TradingRecord(
                LocalDateTime.of(2026, 1, 10, 23, 30), // 날짜
                TradeType.BUY,  // 매수
                390.0,          // 가격 ($)
                10,             // 수량
                "RSI 30 찍어서 1차 진입" // 메모
        ));

        // 2) 추가 매수 (물타기/불타기)
        tesla.addRecord(new TradingRecord(
                LocalDateTime.of(2026, 1, 15, 23, 45),
                TradeType.BUY,
                410.0,
                5,
                "20일선 지지 확인하고 불타기"
        ));

        // 3) 분할 매도
        tesla.addRecord(new TradingRecord(
                LocalDateTime.of(2026, 1, 28, 0, 10),
                TradeType.SELL, // 매도
                450.0,
                7,
                "전고점 도달해서 절반 익절함 😋"
        ));

        stockService.saveStock(tesla); // 종목 저장하면 매매일지도 같이 저장됨!


        // ==========================================
        // 2. 엔비디아 (미장)
        // ==========================================
        Stock nvda = new Stock(
                "엔비디아", "NVDA", MarketType.OVERSEAS,
                List.of("RSI(2)|blue", "60일선|brown", "볼린저밴드|orange"),
                List.of("RSI(2) 10이하 팍 꺾일 때 매수", "20일선 빨간양봉"),
                List.of("RIS(2) 90이상", "RSI 70이상", "전고점 터치 시")
        );
        stockService.saveStock(nvda);


        // ==========================================
        // 3. 삼성전자 (국장) - 매매 기록 포함
        // ==========================================
        Stock samsung = new Stock(
                "삼성전자", "005930", MarketType.DOMESTIC,
                List.of("국장|gray", "배당|yellow"),
                List.of("6만전자 깨지면 줍기"),
                List.of("8만전자 오면 탈출")
        );

        // ➕ 국장 기록 추가
        samsung.addRecord(new TradingRecord(
                LocalDateTime.of(2025, 12, 20, 10, 0),
                TradeType.BUY,
                58000.0, // 원화
                100,
                "바닥인 줄 알고 들어감.."
        ));

        stockService.saveStock(samsung);
    }
}