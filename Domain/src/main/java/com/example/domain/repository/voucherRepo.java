package com.example.domain.repository;

import com.example.domain.entity.voucher;

import java.util.List;
import java.util.Optional;

public interface voucherRepo {
    List<voucher> GetAll();
    Optional<voucher> findById(Integer id);
    Optional<voucher> findByCode(String code);
    voucher save(voucher vc);
    void update(Integer id,voucher vc);
    void delete(voucher vc);

}
