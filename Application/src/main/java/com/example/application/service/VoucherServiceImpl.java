// service/VoucherServiceImpl.java
package com.example.application.service;

import com.example.application.common.PageResult;
import com.example.application.dto.VoucherDto;
import com.example.application.dto.VoucherRequest;
import com.example.domain.entity.voucher;
import com.example.domain.repository.voucherRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    private final voucherRepo voucherRepo;

    public VoucherServiceImpl(voucherRepo voucherRepo) {
        this.voucherRepo = voucherRepo;
    }

    @Override
    public PageResult<VoucherDto> getAll(int page, int size) {
        List<VoucherDto> all = voucherRepo.GetAll()
                .stream()
                .map(this::toDto)
                .toList();

        long total = all.size();
        List<VoucherDto> paged = all.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        return new PageResult<>(paged, page, size, total);
    }

    @Override
    public VoucherDto getById(Integer id) {
        voucher vc = voucherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại: " + id));
        return toDto(vc);
    }

    @Override
    public VoucherDto getByCode(String code) {
        voucher vc = voucherRepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại với code: " + code));
        return toDto(vc);
    }

    @Override
    public VoucherDto create(VoucherRequest request) {
        // Kiểm tra code trùng
        if (voucherRepo.findByCode(request.getCode()).isPresent()) {
            throw new RuntimeException("Code voucher đã tồn tại: " + request.getCode());
        }

        voucher vc = new voucher();
        vc.setCode(request.getCode());
        vc.setDiscout_percent(request.getDiscountPercent());
        vc.setQuantity(request.getQuantity());
        vc.setExpired_date(request.getExpiredDate());
        vc.setStatus(request.getStatus());
        vc.setCreated_At(LocalDateTime.now());
        return toDto(voucherRepo.save(vc));
    }

    @Override
    public VoucherDto update(Integer id, VoucherRequest request) {
        voucherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại: " + id));

        // Kiểm tra code trùng với voucher khác
        voucherRepo.findByCode(request.getCode()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new RuntimeException("Code voucher đã tồn tại: " + request.getCode());
            }
        });

        voucher vc = new voucher();
        vc.setCode(request.getCode());
        vc.setDiscout_percent(request.getDiscountPercent());
        vc.setQuantity(request.getQuantity());
        vc.setExpired_date(request.getExpiredDate());
        vc.setStatus(request.getStatus());
        voucherRepo.update(id, vc);
        return getById(id);
    }
    @Override
    public void delete(Integer id) {
        voucher vc = voucherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại: " + id));
        voucherRepo.delete(vc);
    }

    private VoucherDto toDto(voucher vc) {
        VoucherDto dto = new VoucherDto();
        dto.setId(vc.getId());
        dto.setCode(vc.getCode());
        dto.setDiscountPercent(vc.getDiscout_percent());
        dto.setQuantity(vc.getQuantity());
        dto.setExpiredDate(vc.getExpired_date());
        dto.setStatus(vc.getStatus());
        dto.setCreatedAt(vc.getCreated_At());
        return dto;
    }
}