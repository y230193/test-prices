package com.inditex.api.config;

import com.inditex.application.usecase.FindPriceUseCase;
import com.inditex.domain.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Use cases bean definitions to avoid spring dependencies in application layer.(personal preference)
 */
@Configuration
public class UseCaseConfig {

    @Bean
    public FindPriceUseCase findPriceUseCase(PriceRepository priceRepository) {
        return new FindPriceUseCase(priceRepository);
    }
}
