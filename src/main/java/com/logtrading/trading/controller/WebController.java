package com.logtrading.trading.controller;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final StockService stockService;

    @GetMapping("/")
    public String main(Model model) {
        // 1. 모든 종목 가져와 전달하기
        List<Stock> stocks = stockService.getAllStocks();
        model.addAttribute("stocks", stocks);

        // 2. 태그 중복 제거
        // Map을 써서 "태그이름"이 같으면 덮어쓰기 해버림
        Map<String, String> uniqueTagsMap = new HashMap<>();

        for (Stock stock : stocks) {
            for (String rawTag : stock.getTags()) {
                String tagName;
                String fullTag;

                if (rawTag.contains("|")) {
                    // "장투|red" 인 경우
                    String[] parts = rawTag.split("\\|");
                    tagName = parts[0];
                    fullTag = rawTag;
                } else {
                    // "장투" (옛날 데이터) 인 경우 -> 회색으로 강제 변환
                    tagName = rawTag;
                    fullTag = rawTag + "|gray";
                }

                // 맵에 넣기 (이미 있는 이름이면 덮어씀 -> 즉, 중복 제거됨)
                // 만약 색깔 있는 버전이 나중에 들어오면 색깔 있는 걸로 업데이트 됨
                uniqueTagsMap.put(tagName, fullTag);
            }
        }

        // 맵의 값들(Valus)만 뽑아서 리스트로 보냄
        model.addAttribute("allTags", uniqueTagsMap.values());

        return "index";
    }
}