// dto/VoucherUsageDto.java
package com.example.application.dto;

import java.time.LocalDateTime;

public class VoucherUsageDto {
    private Integer id;
    private Integer userId;
    private Integer voucherId;
    private LocalDateTime usedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getVoucherId() { return voucherId; }
    public void setVoucherId(Integer voucherId) { this.voucherId = voucherId; }
    public LocalDateTime getUsedAt() { return usedAt; }
    public void setUsedAt(LocalDateTime usedAt) { this.usedAt = usedAt; }
}