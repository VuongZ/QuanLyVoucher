package com.example.application.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public class VoucherRequest {

    @NotBlank(message = "Code không được để trống")
    private String code;

    @Min(value = 1, message = "Discount phải từ 1")
    @Max(value = 100, message = "Discount không được vượt quá 100")
    private int discountPercent;

    @Min(value = 0, message = "Quantity không được âm")
    private int quantity;

    @NotNull(message = "Expired date không được để trống")
    @Future(message = "Expired date phải lớn hơn ngày hiện tại")
    private Date expiredDate;

    @NotBlank(message = "Status không được để trống")
    private String status;

    // Getters & Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public int getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(int discountPercent) { this.discountPercent = discountPercent; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Date getExpiredDate() { return expiredDate; }
    public void setExpiredDate(Date expiredDate) { this.expiredDate = expiredDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}