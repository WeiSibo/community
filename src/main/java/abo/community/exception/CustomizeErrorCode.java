package abo.community.exception;

/**
 * @author abo
 * @date 2020/3/2 18:53
 * @remarks
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    POST_NOT_FOUND(2001, "帖子不存在，换一个试试"),
    TARGET_PARAM_NOT_FOUND(2001, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "当前操作需要登录，请登录后重试")
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
