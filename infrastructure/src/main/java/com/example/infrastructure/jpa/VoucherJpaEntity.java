package com.example.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String code;

    @Column(name = "discount_percent", nullable = false)
    private int discountPercent;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "expired_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiredDate;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}