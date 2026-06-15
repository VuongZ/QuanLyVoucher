package com.example.domain.entity;

import java.time.LocalDateTime;

public class voucher_usage {
    private Integer id;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(Integer voucher_id) {
        this.voucher_id = voucher_id;
    }

    public LocalDateTime getUsed_at() {
        return used_at;
    }

    public void setUsed_at(LocalDateTime used_at) {
        this.used_at = used_at;
    }

    private Integer voucher_id;
    private LocalDateTime used_at;
}
