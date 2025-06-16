package com.inditex.application.usecase;

import com.inditex.application.dto.FindPriceDto;
import com.inditex.domain.exceptions.PriceNotFoundException;
import com.inditex.domain.model.Price;
import com.inditex.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindPriceUseCase {

    private final PriceRepository priceRepository;

    public Price findPriceByProductAndBrand(FindPriceDto input) throws PriceNotFoundException {
        Price price = priceRepository.findPriceByProductIdDateAndBrandId(input.productId(), input.brandId(), input.requestDate());

        if (price == null) {
            throw new PriceNotFoundException("Could not find a suitable price for requested date!");
        }

        return price;
    }
}
