package com.logtrading.trading.repository;

import com.logtrading.trading.domain.CalendarMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface CalendarMemoRepository extends JpaRepository<CalendarMemo, Long> {
    // 날짜로 메모 찾기
    Optional<CalendarMemo> findByDate(LocalDate date);
}