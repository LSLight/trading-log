package com.logtrading.trading.service;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.domain.TradeLog;
import com.logtrading.trading.dto.StockResponseDto;
import com.logtrading.trading.repository.StockRepository;
import com.logtrading.trading.repository.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service // 비즈니스 로직 담당
@RequiredArgsConstructor
@Transactional(readOnly = true) // 주로 조회만 하니까 '읽기 전용'으로 성능 최적화
public class StockService {

    private final StockRepository stockRepository;
    private final TradeLogRepository tradeLogRepository; // 일지도 확인할 수 있음

    // 1. 모든 종목 가져오기 (초기 화면용)
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // 2. 검색하기 ("테" -> 테슬라)
    // 검색 기능도 필요하면 DTO 버전으로 만들어야 하는데, 일단은 전체 조회부터!
    public List<Stock> searchStocks(String keyword) {
        return stockRepository.findByStockNameContainingIgnoreCase(keyword);
    }

    public List<StockResponseDto> getAllStockDtos() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockResponseDto> dtos = new ArrayList<>();

        for (Stock stock : stocks) {
            StockResponseDto dto = new StockResponseDto(stock); // 주식가져오고
            List<TradeLog> logs = tradeLogRepository.findByStockName(stock.getStockName()); // 주식로그 가져오기

            dto.setTradeLogs(logs);

            double totalQty = 0;
            double totalCost = 0;
            for (TradeLog log : logs) {
                if ("BUY".equals(log.getType()) || "매수".equals(log.getType())) {
                    // 매수: 수량 늘고, 총 비용 더해서 평단가 재계산 (물타기)
                    totalCost += (log.getPrice() * log.getQuantity());
                    totalQty += log.getQuantity();
                } else if ("SELL".equals(log.getType()) || "매도".equals(log.getType())) {
                    // 매도: 수량만 줄어듦 (평단가는 변하지 않음!)
                    // 남은 비용 = 현재 평단 * 남은 수량
                    double currentAvg = (totalQty == 0) ? 0 : totalCost / totalQty;
                    totalQty -= log.getQuantity();
                    totalCost = currentAvg * totalQty;
                }
            }
            dto.setTotalQuantity(totalQty);
            dto.setAvgPrice(totalQty > 0.0001 ? totalCost / totalQty : 0);
            dto.setInvestedAmount(totalCost);
            dtos.add(dto);

        }
        return dtos;
    }


}