package com.logtrading.trading.controller;

import com.logtrading.trading.domain.TradeLog;
import com.logtrading.trading.service.StockService;
import com.logtrading.trading.service.TradeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller // HTML
@RequiredArgsConstructor
public class WebController {

    private final StockService stockService;
    private final TradeLogService tradeLogService;

    // 메인 화면 (대시보드)
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("stocks", stockService.getAllStockDtos());
        return "index";
    }

    //저장하기
    @PostMapping("/log/new")
    public String saveLog(@RequestParam String stockName,
                          @RequestParam String type,
                          @RequestParam Double price,
                          @RequestParam Double quantity,
                          @RequestParam String memo) {

        TradeLog newLog = new TradeLog();
        newLog.setDate(LocalDate.now());
        newLog.setStockName(stockName);
        newLog.setType(type);
        newLog.setPrice(price);
        newLog.setQuantity(quantity);
        newLog.setMemo(memo);

        tradeLogService.saveLog(newLog);

        return "redirect:/"; // 저장 끝나면 메인화면 새로고침
    }
}