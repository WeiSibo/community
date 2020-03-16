package abo.community.exception;

/**
 * @author abo
 * @date 2020/3/2 18:53
 * @remarks
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /*
        帖子不存在
        操作目标不存在
        没有登陆
        系统错误
        类型参数错误
     */
    POST_NOT_FOUND(2001, "帖子不存在，换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "当前操作需要登录，请登录后重试"),
    SYSTEM_ERROR(2004, "服务器炸了，请稍后再试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "评论不存在，换一个试试"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空")
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
