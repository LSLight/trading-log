package com.logtrading.trading.service;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logtrading.trading.repository.TradingRecordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;
    private final TradingRecordRepository tradingRecordRepository;

    public void saveStock(Stock stock) {
        stockRepository.save(stock); }

    @Transactional(readOnly = true)
    public List<Stock> getAllStocks() {
        return stockRepository.findAll(); }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id); }

    public void deleteAll() {
        stockRepository.deleteAll(); }


    // ⭐ 태그 이름 일괄 변경 (예: 볼밴 -> 볼린저밴드)
    public void renameTag(String oldName, String newName) {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {
            List<String> newTags = new ArrayList<>();
            boolean changed = false;

            for (String tag : stock.getTags()) {
                // 태그 포맷: "이름|색상"
                String[] parts = tag.split("\\|");
                String tagName = parts[0];
                String tagColor = parts.length > 1 ? parts[1] : "gray";

                if (tagName.equals(oldName)) {
                    // 이름이 같으면 새 이름으로 교체!
                    newTags.add(newName + "|" + tagColor);
                    changed = true;
                } else {
                    newTags.add(tag);
                }
            }

            if (changed) {
                stock.setTags(newTags); // 변경된 리스트로 덮어쓰기 (자동 저장됨)
            }
        }
    }

    // 저장로직 추가
    // 1. 매매 기록 저장하기
    public void addTradeRecord(Long stockId, com.logtrading.trading.dto.TradingRecordDto dto) {
        // 1. 종목 찾기
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("종목이 없습니다."));

        // 2. 날짜 변환 (String "2026-01-28" -> LocalDateTime)
        // 시간은 현재 시간으로 대충 맞춤 (나중에 정교하게 수정 가능)
        java.time.LocalDateTime date = java.time.LocalDate.parse(dto.getDate()).atStartOfDay();

        // 3. 기록 엔티티 생성
        com.logtrading.trading.domain.TradingRecord record = new com.logtrading.trading.domain.TradingRecord(
                date, dto.getType(), dto.getPrice(), dto.getQuantity(), dto.getMemo()
        );

        // 4. 종목과 연결 (Stock 엔티티에 만들어둔 편의 메서드 사용)
        stock.addRecord(record);

        // 5. 저장 (Stock을 저장하면 연결된 Record도 같이 저장됨 - Cascade 옵션 덕분)
        stockRepository.save(stock);
    }

    // ⭐ 매매 기록 삭제 기능
    public void deleteTradeRecord(Long recordId) {
        tradingRecordRepository.deleteById(recordId);
    }
}