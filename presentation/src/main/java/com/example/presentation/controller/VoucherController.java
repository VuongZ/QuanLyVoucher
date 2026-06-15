package com.example.presentation.controller;

import com.example.application.common.ApiResponse;
import com.example.application.common.PageResult;
import com.example.application.dto.VoucherDto;
import com.example.application.dto.VoucherRequest;
import com.example.application.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<VoucherDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách voucher thành công", voucherService.getAll(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VoucherDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Lấy voucher thành công", voucherService.getById(id)));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<VoucherDto>> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(ApiResponse.success("Lấy voucher thành công", voucherService.getByCode(code)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VoucherDto>> create(@Valid @RequestBody VoucherRequest request) {
        return ResponseEntity.status(201).body(ApiResponse.success("Tạo voucher thành công", voucherService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VoucherDto>> update(
            @PathVariable Integer id,
            @Valid @RequestBody VoucherRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Cập nhật voucher thành công", voucherService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        voucherService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa voucher thành công"));
    }
}