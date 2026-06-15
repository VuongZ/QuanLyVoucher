package com.example.infrastructure.mapper;

import com.example.domain.entity.voucher;
import com.example.infrastructure.jpa.VoucherJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {
    public static voucher toDomain(VoucherJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        voucher d = new voucher();
        d.setId(entity.getId());
        d.setCode(entity.getCode());
        d.setDiscout_percent(entity.getDiscountPercent()); // Chú ý map đúng tên biến domain
        d.setQuantity(entity.getQuantity());
        d.setExpired_date(entity.getExpiredDate());
        d.setStatus(entity.getStatus());
        d.setCreated_At(entity.getCreatedAt());
        return d;
    }
    public static VoucherJpaEntity toJpa(voucher domain) {
        if (domain == null) {
            return null;
        }

        VoucherJpaEntity d = new VoucherJpaEntity();
        d.setId(domain.getId());
        d.setCode(domain.getCode());
        d.setDiscountPercent(domain.getDiscout_percent());
        d.setQuantity(domain.getQuantity());
        d.setExpiredDate(domain.getExpired_date());
        d.setStatus(domain.getStatus());
        d.setCreatedAt(domain.getCreated_At());
        return d;
    }
}
