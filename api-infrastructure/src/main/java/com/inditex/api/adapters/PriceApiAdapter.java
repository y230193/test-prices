package com.inditex.api.adapters;

import com.inditex.api.mapper.PriceApiMapper;
import com.inditex.api.model.PriceRequestDto;
import com.inditex.api.model.PriceResponseDto;
import com.inditex.api.ports.PriceApi;
import com.inditex.application.usecase.FindPriceUseCase;
import com.inditex.domain.exceptions.PriceNotFoundException;
import com.inditex.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PriceApiAdapter implements PriceApi {

    private final FindPriceUseCase findPriceUseCase;

    private final PriceApiMapper priceApiMapper;

    @Override
    public ResponseEntity<PriceResponseDto> getPrice(PriceRequestDto priceRequest) {
        try {
            Price price = findPriceUseCase.findPriceByProductAndBrand(
                    priceApiMapper.mapPriceRequestToDomain(priceRequest));
            return ResponseEntity.ok(priceApiMapper.mapPriceToApiResponse(price));
        } catch (PriceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
