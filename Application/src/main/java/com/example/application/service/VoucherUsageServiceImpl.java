// service/VoucherUsageServiceImpl.java
package com.example.application.service;

import com.example.application.common.PageResult;
import com.example.application.dto.VoucherUsageDto;
import com.example.application.dto.VoucherUsageRequest;
import com.example.domain.entity.voucher;
import com.example.domain.entity.voucher_usage;
import com.example.domain.repository.voucherRepo;
import com.example.domain.repository.voucher_usageRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class VoucherUsageServiceImpl implements VoucherUsageService {

    private final voucher_usageRepo usageRepo;
    private final voucherRepo voucherRepo;

    public VoucherUsageServiceImpl(voucher_usageRepo usageRepo, voucherRepo voucherRepo) {
        this.usageRepo = usageRepo;
        this.voucherRepo = voucherRepo;
    }

    @Override
    public PageResult<VoucherUsageDto> getAll(int page, int size) {
        List<VoucherUsageDto> all = usageRepo.GetAll()
                .stream()
                .map(this::toDto)
                .toList();

        long total = all.size();
        List<VoucherUsageDto> paged = all.stream()
                .skip((long) page * size)
                .limit(size)
                .toList();

        return new PageResult<>(paged, page, size, total);
    }

    @Override
    public VoucherUsageDto getById(Integer id) {
        voucher_usage u = usageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("VoucherUsage không tồn tại: " + id));
        return toDto(u);
    }

    @Override
    public VoucherUsageDto create(VoucherUsageRequest request) {
        // Lấy voucher
        voucher vc = voucherRepo.findById(request.getVoucherId())
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));

        // Kiểm tra hết hạn
        if (vc.getExpired_date().before(new Date())) {
            throw new RuntimeException("Voucher đã hết hạn");
        }

        // Kiểm tra status
        if (!"ACTIVE".equalsIgnoreCase(vc.getStatus())) {
            throw new RuntimeException("Voucher không còn hiệu lực");
        }

        // Kiểm tra số lượng
        if (vc.getQuantity() <= 0) {
            throw new RuntimeException("Voucher đã hết lượt sử dụng");
        }

        // Giảm quantity đi 1
        vc.setQuantity(vc.getQuantity() - 1);
        voucherRepo.save(vc);

        // Lưu lịch sử
        voucher_usage u = new voucher_usage();
        u.setUser_id(request.getUserId());
        u.setVoucher_id(request.getVoucherId());
        u.setUsed_at(LocalDateTime.now());
        return toDto(usageRepo.Save(u));
    }

    private VoucherUsageDto toDto(voucher_usage u) {
        VoucherUsageDto dto = new VoucherUsageDto();
        dto.setId(u.getId());
        dto.setUserId(u.getUser_id());
        dto.setVoucherId(u.getVoucher_id());
        dto.setUsedAt(u.getUsed_at());
        return dto;
    }
}