// dto/VoucherUsageRequest.java
package com.example.application.dto;

public class VoucherUsageRequest {
    private Integer userId;
    private Integer voucherId;

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getVoucherId() { return voucherId; }
    public void setVoucherId(Integer voucherId) { this.voucherId = voucherId; }
}