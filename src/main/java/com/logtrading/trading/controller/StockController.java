package com.logtrading.trading.controller;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 데이터 받아서 보여줌(JSON)"
@RequestMapping("/api/stocks") //  "localhost:8080/api/stocks"가 기본
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    // 1. 모든 종목 보이기.
    // 주소: GET http://localhost:8080/api/stocks
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    // 2. 검색해줘!
    // 주소: GET http://localhost:8080/api/stocks/search?keyword=엔비디아
    @GetMapping("/search")
    public List<Stock> searchStocks(@RequestParam String keyword) {
        return stockService.searchStocks(keyword);
    }
}