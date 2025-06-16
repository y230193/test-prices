package com.inditex.db.infrastructure.adapters;

import com.inditex.db.infrastructure.entity.PriceEntity;
import com.inditex.db.infrastructure.mapper.PriceMapper;
import com.inditex.db.infrastructure.repository.PriceJpaRepository;
import com.inditex.domain.model.Price;
import com.inditex.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    private final PriceMapper priceMapper;

    @Override
    public Price findPriceByProductIdDateAndBrandId(Long productId, Long brandId, LocalDateTime date) {
        Optional<PriceEntity> priceEntity = priceJpaRepository.findPriceByProductIdDateAndBrandId(productId, brandId, date);
        if (priceEntity.isPresent()) {
            return priceMapper.mapToDomain(priceEntity.get());
        }
        return null;
    }
}
