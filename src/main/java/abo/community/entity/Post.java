package abo.community.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author abo
 * @date 2020/2/18 20:49
 * @remarks
 **/
@Data
@TableName(value = "post")
public class Post {
    @TableId(value = "id")
    private Integer id;
    private String title;
    private String context;
    private String tag;
    @TableField(value = "gmt_create")
    private Long gmtCreate;
    @TableField(value = "gmt_modified")
    private Long gmtModified;
    private Integer creator;
    @TableField(value = "view_count")
    private Integer viewCount;
    @TableField(value = "comment_count")
    private Integer commentCount;
    @TableField(value = "like_count")
    private Integer likeCount;

}
