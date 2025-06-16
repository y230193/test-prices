package com.inditex.db.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PRICES")
@Data
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "BRAND_ID")
    private Long brandId;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private Integer priceList;
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "PRIORITY")
    private Boolean priority;
    @Column(name = "PRICE", precision = 6, scale = 2)
    private BigDecimal price;
    @Column(name = "CURR", columnDefinition = "CHAR(3)")
    private String currency;
}
