package com.example.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherUsageJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "voucher_id", nullable = false)
    private Integer voucherId;

    @Column(name = "used_at")
    private LocalDateTime usedAt;
}