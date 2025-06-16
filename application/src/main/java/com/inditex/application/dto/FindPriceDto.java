package com.inditex.application.dto;

import java.time.LocalDateTime;

public record FindPriceDto(Long productId, Long brandId, LocalDateTime requestDate) {
}
