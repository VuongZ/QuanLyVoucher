package com.example.domain.entity;


import java.time.LocalDateTime;
import java.util.Date;

public class voucher {
    private  Integer id;
    private  String code;
    private int discout_percent;
    private  int quantity;
    private Date expired_date;
    private String status;
    private LocalDateTime Created_At;
    private boolean is_deleted;
    private LocalDateTime deleted_at;

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscout_percent() {
        return discout_percent;
    }

    public void setDiscout_percent(int discout_percent) {
        this.discout_percent = discout_percent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreated_At() {
        return Created_At;
    }

    public void setCreated_At(LocalDateTime created_At) {
        Created_At = created_At;
    }
}
