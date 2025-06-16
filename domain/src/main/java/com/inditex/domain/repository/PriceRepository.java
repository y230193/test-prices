package com.inditex.domain.repository;

import com.inditex.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price findPriceByProductIdDateAndBrandId(Long productId, Long brandId, LocalDateTime date);
}
