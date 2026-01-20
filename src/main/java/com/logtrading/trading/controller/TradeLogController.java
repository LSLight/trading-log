package com.logtrading.trading.controller;

import com.logtrading.trading.domain.TradeLog;
import com.logtrading.trading.service.TradeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs") // "localhost:8080/api/logs" 주소 담당
@RequiredArgsConstructor
public class TradeLogController {

    private final TradeLogService tradeLogService;

    // 1. 매매 기록 저장
    // 주소: POST http://localhost:8080/api/logs
    @PostMapping
    public String saveLog(@RequestBody TradeLog log) {
        tradeLogService.saveLog(log);
        return "✅ 기록 저장 완료!";
    }

    // 2. 특정 종목 기록 조회
    // 주소: GET http://localhost:8080/api/logs/엔비디아
    @GetMapping("/{stockName}")
    public List<TradeLog> getLogsByStock(@PathVariable String stockName) {
        return tradeLogService.getLogsByStock(stockName);
    }

    // 3. 기록 삭제
    // 주소: DELETE http://localhost:8080/api/logs/1
    @DeleteMapping("/{id}")
    public String deleteLog(@PathVariable Long id) {
        tradeLogService.deleteLog(id);
        return "🗑️ 기록 삭제 완료!";
    }
}