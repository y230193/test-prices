package com.inditex.api.adapters;

import com.inditex.ApiTestApplication;
import com.inditex.api.config.UseCaseConfig;
import com.inditex.api.model.PriceRequestDto;
import com.inditex.api.model.PriceResponseDto;
import com.inditex.domain.model.Price;
import com.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ApiTestApplication.class, UseCaseConfig.class})
class PriceApiAdapterTest {

    @Autowired
    private PriceApiAdapter priceApiAdapter;

    @MockitoBean
    private PriceRepository priceRepository;

    @Test
    void testGetPrice() {
        //Given
        PriceRequestDto priceRequestDto = new PriceRequestDto(35455L, 1L, OffsetDateTime.now());
        Price price = new Price(35455L, 1L, 1, LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1), new BigDecimal(60), "EUR");
        when(priceRepository.findPriceByProductIdDateAndBrandId(priceRequestDto.getProductId(),
                priceRequestDto.getBrandId(), priceRequestDto.getRequestDate().toLocalDateTime())).thenReturn(price);

        //When
        ResponseEntity<PriceResponseDto> response = priceApiAdapter.getPrice(priceRequestDto);

        //Then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        PriceResponseDto priceResponseDto = response.getBody();
        assertEquals(35455L, priceResponseDto.getProductId());
        assertEquals(1L, priceResponseDto.getBrandId());
        assertEquals(1, priceResponseDto.getRate());
        assertEquals(BigDecimal.valueOf(60), priceResponseDto.getPrice());
        assertEquals("EUR", priceResponseDto.getCurrency());
        assertEquals(OffsetDateTime.of(price.startDate(), ZoneOffset.UTC), priceResponseDto.getStartDate());
        assertEquals(OffsetDateTime.of(price.endDate(), ZoneOffset.UTC), priceResponseDto.getEndDate());
    }

    @Test
    void testGetPriceNotFound() {
        //Given
        PriceRequestDto priceRequestDto = new PriceRequestDto(35455L, 1L, OffsetDateTime.now());
        when(priceRepository.findPriceByProductIdDateAndBrandId(priceRequestDto.getProductId(),
                priceRequestDto.getBrandId(), priceRequestDto.getRequestDate().toLocalDateTime())).thenReturn(null);

        //When
        ResponseEntity<PriceResponseDto> response = priceApiAdapter.getPrice(priceRequestDto);

        //Then
        assertTrue(response.getStatusCode().is4xxClientError());
        assertNull(response.getBody());
    }
}
