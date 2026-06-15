// application/common/ApiResponse.java
package com.example.application.common;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    // Constructor thành công có data
    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = true;
        res.message = message;
        res.data = data;
        return res;
    }

    // Constructor thành công không có data
    public static <T> ApiResponse<T> success(String message) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = true;
        res.message = message;
        return res;
    }

    // Constructor lỗi
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = false;
        res.message = message;
        return res;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}