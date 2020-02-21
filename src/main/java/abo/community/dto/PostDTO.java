package abo.community.dto;

import abo.community.entity.User;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author abo
 * @date 2020/2/19 21:49
 * @remarks
 **/
@Data
public class PostDTO {
    private Integer id;
    private String title;
    private String context;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
