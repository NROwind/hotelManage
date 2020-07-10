package org.csu.hotel.exception;

/**
 * 返回信息模板
 */
public class ErrorResponseEntity {

    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponseEntity(int code) {
        this.code = code;
    }

    public ErrorResponseEntity(String message) {
        this.message = message;
    }

    // 省略 get set 方

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
