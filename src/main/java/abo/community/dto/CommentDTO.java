package abo.community.dto;

import lombok.Data;

/**
 * @author abo
 * @date 2020/3/3 22:23
 * @remarks
 **/
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
