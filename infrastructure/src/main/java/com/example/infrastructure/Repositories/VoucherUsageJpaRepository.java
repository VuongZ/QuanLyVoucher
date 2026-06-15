package com.example.infrastructure.Repositories;

import com.example.infrastructure.jpa.VoucherUsageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherUsageJpaRepository extends JpaRepository<VoucherUsageJpaEntity,Integer> {
}
