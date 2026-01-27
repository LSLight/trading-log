package com.logtrading.trading.init;

import com.logtrading.trading.domain.MarketType;
import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.service.StockService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final StockService stockService;

    @PostConstruct
    public void init() {
        stockService.deleteAll();

        // 미장 예시
        Stock tesla = new Stock(
                "테슬라", "TSLA", MarketType.OVERSEAS,
                List.of("RSI|purple", "볼린저밴드|yellow"),
                // ⭐ 전략을 여러 줄(List)로 입력!
                //매수
                List.of("RSI 30-40 줍줍", "테슬라 망했다 소리 나올때 추매"),
                //매도
                List.of("전고점 돌파시 분할 매도", "볼밴 상단 터치")
        );
        stockService.saveStock(tesla);

        // 국장 예시
        Stock samsung = new Stock(
                "한미반도체", "", MarketType.DOMESTIC,
                List.of("20일선|green"),
                //매수
                List.of("20일선 닿고 아래꼬리달때"),
                //매도
                List.of("전 고점 부근에서 매도","20일선 이탈시 비중 줄이기")
        );
        stockService.saveStock(samsung);
    }
}