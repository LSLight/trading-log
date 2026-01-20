package com.logtrading.trading.init;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.domain.TradeLog;
import com.logtrading.trading.repository.StockRepository;
import com.logtrading.trading.repository.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component // Spring에서 자동으로 관리
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final StockRepository stockRepository;
    private final TradeLogRepository tradeLogRepository;

    @Override
    public void run(String... args) throws Exception {
        // 프로그램 시작 시 실행되는 곳
        // 데이터가 하나도 없을 때만 실행 (중복 방지)
        if (stockRepository.count() == 0) {
            System.out.println("🚀 기초 종목 데이터를 생성합니다...");

            // 1. 엔비디아
            stockRepository.save(new Stock(
                    "엔비디아",
                    "NVDA",
                    "https://logo.clearbit.com/nvidia.com",
                    "20일선 지지 시 진입",
                    "전고점 돌파 실패 시 매도",
                    "20일선"
            ));

            // 2. 삼성전자 (국장 예시)
            stockRepository.save(new Stock(
                    "삼성전자",
                    "005930",
                    "https://logo.clearbit.com/samsung.com",
                    "6만전자 깨지면 줍줍",
                    "8만전자 오면 감사",
                    "주가"
            ));

            stockRepository.save(new Stock("테슬라", "TSLA", "https://logo.clearbit.com/tesla.com", "RSI 30...", "RSI 70...", "2차전지"));

            // 테슬라 매수 기록 2개
            tradeLogRepository.save(new TradeLog(LocalDate.now(), "BUY", "테슬라", 400.0, 1.0, "1차 진입"));
            tradeLogRepository.save(new TradeLog(LocalDate.now(), "BUY", "테슬라", 300.0, 1.0, "물타기"));
            // -> 예상 결과: 평단 $350, 수량 2주

            // 엔비디아 매수 기록 1개
            tradeLogRepository.save(new TradeLog(LocalDate.now(), "BUY", "엔비디아", 130.0, 10.0, "몰빵"));

            System.out.println("✅ 기초 데이터 + 테스트용 매매일지 생성 완료!");
        }
    }
}