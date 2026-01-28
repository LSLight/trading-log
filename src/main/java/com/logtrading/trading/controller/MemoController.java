package com.logtrading.trading.controller;

import com.logtrading.trading.domain.CalendarMemo;
import com.logtrading.trading.repository.CalendarMemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
public class MemoController {

    private final CalendarMemoRepository memoRepository;

    // 1. 메모 조회 (없으면 빈 문자열 반환)
    @GetMapping("/{dateStr}")
    public String getMemo(@PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return memoRepository.findByDate(date)
                .map(CalendarMemo::getContent)
                .orElse("");
    }

    // 2. 메모 저장
    @PostMapping
    public String saveMemo(@RequestBody Map<String, String> payload) {
        LocalDate date = LocalDate.parse(payload.get("date"));
        String content = payload.get("content");

        CalendarMemo memo = memoRepository.findByDate(date)
                .orElse(new CalendarMemo(date, ""));

        memo.setContent(content);
        memoRepository.save(memo);
        return "ok";
    }
}