package edu.sgu.delicatessen.exception;

public class ApiException  extends RuntimeException {
    private int code;

    public ApiException(String message, int code) {
        super(message);
        this.code = code;
    }
    public ApiException(Exception e) {
        super(e.getMessage());
        this.code = 404;
    }
    public static ApiException invalidField(String field) {
      return new ApiException("invalid"+field,400);
    }
    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
