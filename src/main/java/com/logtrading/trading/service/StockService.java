package com.logtrading.trading.service;

import com.logtrading.trading.domain.Stock;
import com.logtrading.trading.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    public void saveStock(Stock stock) { stockRepository.save(stock); }

    @Transactional(readOnly = true)
    public List<Stock> getAllStocks() { return stockRepository.findAll(); }

    public void deleteStock(Long id) { stockRepository.deleteById(id); }

    public void deleteAll() { stockRepository.deleteAll(); }


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
}