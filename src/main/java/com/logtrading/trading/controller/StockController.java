package com.logtrading.trading.controller;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    // 1. 종목 카드 저장 (POST)
    @PostMapping
    public String saveStock(@RequestBody Stock stock) {
        stockService.saveStock(stock);
        return "✅ 종목 카드 생성 완료!";
    }

    // 2. 전체 카드 목록 조회 (GET)
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    // 3. 종목 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return "🗑️ 삭제 완료!";
    }

    // ⭐ 4. 태그 이름 변경 API
    @PutMapping("/tags")
    public String updateTag(@RequestBody Map<String, String> payload) {
        String oldName = payload.get("oldName");
        String newName = payload.get("newName");
        stockService.renameTag(oldName, newName);
        return "ok";
    }

    // 매매 기록 저장 API
    @PostMapping("/{stockId}/records")
    public String addRecord(@PathVariable Long stockId, @RequestBody com.logtrading.trading.dto.TradingRecordDto dto) {
        stockService.addTradeRecord(stockId, dto);
        return "ok";
    }

    // ⭐ 매매 기록 삭제 API
    @DeleteMapping("/records/{recordId}")
    public String deleteRecord(@PathVariable Long recordId) {
        stockService.deleteTradeRecord(recordId);
        return "ok";
    }
}
