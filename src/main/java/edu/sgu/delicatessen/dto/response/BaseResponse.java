package edu.sgu.delicatessen.dto.response;

public class BaseResponse <T>{
    private Integer statusCode;
    private String error;
    private T data;

    public BaseResponse(T data) {
        this.statusCode = 200;
        this.error = "";
        this.data = data;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
