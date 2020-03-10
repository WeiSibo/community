package abo.community.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author abo
 * @date 2020/3/2 22:39
 * @remarks
 **/
@Data
@TableName(value = "comment")
public class Comment {
    @TableId(value = "id")
    private Integer id;
    @TableField(value = "parent_id")
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    @TableField(value = "gmt_create")
    private Long gmtCreate;
    @TableField(value = "gmt_modified")
    private Long gmtModified;
    @TableField(value = "like_count")
    private Integer likeCount;
    private String content;
}
