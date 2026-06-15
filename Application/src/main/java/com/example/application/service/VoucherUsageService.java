package com.example.application.service;

import com.example.application.common.PageResult;
import com.example.application.dto.VoucherUsageDto;
import com.example.application.dto.VoucherUsageRequest;

public interface VoucherUsageService {
    PageResult<VoucherUsageDto> getAll(int page, int size);
    VoucherUsageDto getById(Integer id);
    VoucherUsageDto create(VoucherUsageRequest request);
}