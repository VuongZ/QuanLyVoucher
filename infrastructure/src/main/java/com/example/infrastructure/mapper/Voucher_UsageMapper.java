package com.example.infrastructure.mapper;

import com.example.domain.entity.voucher_usage;
import com.example.infrastructure.jpa.VoucherUsageJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class Voucher_UsageMapper {
        public static voucher_usage toDomain(VoucherUsageJpaEntity entity) {
            if (entity == null) {
                return null;
            }

            voucher_usage d = new voucher_usage();
            d.setId(entity.getId());
            d.setUser_id(entity.getUserId()); // Ánh xạ từ userId (JPA) sang user_id (Domain)
            d.setVoucher_id(entity.getVoucherId());
            d.setUsed_at(entity.getUsedAt());
            return d;
        }

        public static VoucherUsageJpaEntity toJpa(voucher_usage domain) {
            if (domain == null) {
                return null;
            }

            VoucherUsageJpaEntity d = new VoucherUsageJpaEntity();
            d.setId(domain.getId());
            d.setUserId(domain.getUser_id());
            d.setVoucherId(domain.getVoucher_id());
            d.setUsedAt(domain.getUsed_at());
            return d;
        }
}
