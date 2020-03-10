package abo.community.enums;

/**
 * @author abo
 * @date 2020/3/4 10:01
 * @remarks
 **/
public enum CommentTypeEnum {
    POST(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type = type;
    }
}
