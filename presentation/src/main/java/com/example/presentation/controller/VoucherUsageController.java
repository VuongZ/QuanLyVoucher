package com.example.presentation.controller;

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
    public ResponseEntity<PageResult<VoucherUsageDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(voucherUsageService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherUsageDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(voucherUsageService.getById(id));
    }

    @PostMapping
    public ResponseEntity<VoucherUsageDto> create(@RequestBody VoucherUsageRequest request) {
        return ResponseEntity.status(201).body(voucherUsageService.create(request));
    }
}