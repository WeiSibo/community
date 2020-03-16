package abo.community.exception;

/**
 * @author abo
 * @date 2020/3/2 18:03
 * @remarks
 **/
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }
}
