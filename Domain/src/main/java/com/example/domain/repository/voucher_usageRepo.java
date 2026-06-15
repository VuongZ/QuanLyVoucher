package com.example.domain.repository;

import com.example.domain.entity.user;
import com.example.domain.entity.voucher_usage;

import java.util.List;
import java.util.Optional;

public interface voucher_usageRepo {
    List<voucher_usage> GetAll();
    Optional<voucher_usage> findById(Integer id);
    voucher_usage Save(voucher_usage u);
}
