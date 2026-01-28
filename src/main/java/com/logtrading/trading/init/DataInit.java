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
                "SK하이닉스", "", MarketType.DOMESTIC,
                List.of(" 주가|green","RSI|purple"),
                //매수
                List.of("80만원 초로 내려올 때 진입","RSI 내려가는거 보고 추매"),
                //매도
                List.of("일단 가지고있기")
        );
        stockService.saveStock(samsung);
    }
}