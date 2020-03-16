package abo.community.enums;

/**
 * @author abo
 * @date 2020/3/4 10:01
 * @remarks
 **/
public enum CommentTypeEnum {
    /*
        评论类型
     */
    POST(1),
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for(CommentTypeEnum commentTypeEnum: CommentTypeEnum.values()){
            if(commentTypeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type = type;
    }
}
