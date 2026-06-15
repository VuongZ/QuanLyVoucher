package com.example.presentation.controller;

import com.example.application.common.ApiResponse;
import com.example.application.common.PageResult;
import com.example.application.dto.VoucherUsageDto;
import com.example.application.dto.VoucherUsageRequest;
import com.example.application.service.VoucherUsageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voucher-usages")
public class VoucherUsageController {

    private final VoucherUsageService voucherUsageService;

    public VoucherUsageController(VoucherUsageService voucherUsageService) {
        this.voucherUsageService = voucherUsageService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<VoucherUsageDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách lịch sử thành công", voucherUsageService.getAll(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VoucherUsageDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Lấy lịch sử thành công", voucherUsageService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VoucherUsageDto>> create(@RequestBody VoucherUsageRequest request) {
        return ResponseEntity.status(201).body(ApiResponse.success("Sử dụng voucher thành công", voucherUsageService.create(request)));
    }
}