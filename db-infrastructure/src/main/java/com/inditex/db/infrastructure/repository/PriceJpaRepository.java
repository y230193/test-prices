package com.inditex.db.infrastructure.repository;

import com.inditex.db.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, UUID> {

    @Query("FROM PriceEntity p where p.productId = :productId and p.brandId= :brandId and :date between p.startDate and p.endDate order by p.priority desc limit 1")
    Optional<PriceEntity> findPriceByProductIdDateAndBrandId(Long productId, Long brandId, LocalDateTime date);
}
