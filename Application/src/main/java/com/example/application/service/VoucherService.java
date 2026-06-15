// service/VoucherService.java
package com.example.application.service;

import com.example.application.common.PageResult;
import com.example.application.dto.VoucherDto;
import com.example.application.dto.VoucherRequest;

public interface VoucherService {
    PageResult<VoucherDto> getAll(int page, int size);
    VoucherDto getById(Integer id);
    VoucherDto getByCode(String code);
    VoucherDto create(VoucherRequest request);
    VoucherDto update(Integer id, VoucherRequest request);
    void delete(Integer id);
}