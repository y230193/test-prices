package com.inditex.db.infrastructure.adapters;


import com.inditex.DbTestApplication;
import com.inditex.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:application-dbtest.properties")
@SpringBootTest(classes = DbTestApplication.class)
@ActiveProfiles("dbtest")
class PriceRepositoryAdapterTest {

    @Autowired
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Sql("/data-db-infrastructure.sql")
    @Test
    void findPriceByProductIdDateAndBrandIdTest() {
        // Given
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime requestDate = LocalDateTime.of(2020, 06, 14, 10, 00);
        // When
        Price result = priceRepositoryAdapter.findPriceByProductIdDateAndBrandId(productId, brandId, requestDate);

        // Then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(35.50).setScale(2), result.price());
        assertEquals("EUR", result.currency());
        assertEquals(productId, result.productId());
        assertEquals(brandId, result.brandId());
        assertEquals(LocalDateTime.of(2020, 06, 14, 00, 00, 00), result.startDate());
        assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59, 59), result.endDate());
        assertEquals(1, result.rate());

    }

    @Test
    void findPriceByProductIdDateAndBrandIdNotFoundTest() {
        // Given
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime requestDate = LocalDateTime.of(2025, 06, 14, 10, 00);
        // When
        Price result = priceRepositoryAdapter.findPriceByProductIdDateAndBrandId(productId, brandId, requestDate);

        // Then
        assertNull(result);
    }
}
