package com.logtrading.trading.service;

import com.logtrading.trading.domain.TradeLog;
import com.logtrading.trading.repository.TradeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TradeLogService {

    private final TradeLogRepository tradeLogRepository;

    // 1. 매매 기록 저장하기 (Create)
    public void saveLog(TradeLog log) {
        tradeLogRepository.save(log);
    }

    // 2. 특정 종목의 기록만 가져오기 (Read)
    // 예: "테슬라" 클릭 -> 테슬라 매매일지 쫙 보여주기
    @Transactional(readOnly = true)
    public List<TradeLog> getLogsByStock(String stockName) {
        return tradeLogRepository.findByStockName(stockName);
    }

    // 3. 기록 삭제하기 (Delete)
    public void deleteLog(Long id) {
        tradeLogRepository.deleteById(id);
    }
}