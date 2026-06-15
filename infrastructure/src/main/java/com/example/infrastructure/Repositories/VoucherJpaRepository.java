package com.example.infrastructure.Repositories;

import com.example.infrastructure.jpa.VoucherJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherJpaRepository extends JpaRepository<VoucherJpaEntity,Integer> {
    Optional<VoucherJpaEntity> findByCode(String code);

}
