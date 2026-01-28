package com.logtrading.trading.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class CalendarMemo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // 날짜 하나당 일기 하나 (중복 방지)
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String content;

    public CalendarMemo(LocalDate date, String content) {
        this.date = date;
        this.content = content;
    }
}