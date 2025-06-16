package com.inditex.domain.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(Long productId, Long brandId, Integer rate, LocalDateTime startDate, LocalDateTime endDate,
                    BigDecimal price, String currency) {
}

