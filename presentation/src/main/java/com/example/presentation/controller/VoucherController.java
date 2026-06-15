package com.example.presentation.controller;


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
    public ResponseEntity<PageResult<VoucherDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(voucherService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(voucherService.getById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<VoucherDto> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(voucherService.getByCode(code));
    }

    @PostMapping
    public ResponseEntity<VoucherDto> create (@Valid @RequestBody  VoucherRequest request) {
        return ResponseEntity.status(201).body(voucherService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoucherDto> update(
            @PathVariable Integer id,
            @RequestBody VoucherRequest request) {
        return ResponseEntity.ok(voucherService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid  @PathVariable Integer id) {
        voucherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}