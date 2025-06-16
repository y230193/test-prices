package com.inditex.application.usecase;

import com.inditex.application.dto.FindPriceDto;
import com.inditex.domain.exceptions.PriceNotFoundException;
import com.inditex.domain.model.Price;
import com.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindPriceUseCaseTest {

    private PriceRepository priceRepository;
    private FindPriceUseCase useCase;

    @BeforeEach
    public void setUp() {
        priceRepository = Mockito.mock(PriceRepository.class);
        useCase = new FindPriceUseCase(priceRepository);
    }

    @Test
    void findPriceByProductAndBrandTest() throws PriceNotFoundException {
        //Given
        FindPriceDto findPriceDto = new FindPriceDto(35455L, 1L, LocalDateTime.now());
        Price price = new Price(35455L, 1L, 1, LocalDateTime.now().minusHours(1),
                LocalDateTime.now().plusHours(1), new BigDecimal(60), "EUR");
        when(priceRepository.findPriceByProductIdDateAndBrandId(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(price);
        //When
        Price result = useCase.findPriceByProductAndBrand(findPriceDto);

        //Then
        verify(priceRepository).findPriceByProductIdDateAndBrandId(anyLong(), anyLong(), any(LocalDateTime.class));
        assertEquals(result, price);
    }

    @Test
    void findPriceByProductAndBrandNotFoundTest() {
        //Given
        FindPriceDto findPriceDto = new FindPriceDto(35455L, 1L, LocalDateTime.now());
        when(priceRepository.findPriceByProductIdDateAndBrandId(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(null);
        //When
        assertThrows(PriceNotFoundException.class, () -> useCase.findPriceByProductAndBrand(findPriceDto));

    }
}
