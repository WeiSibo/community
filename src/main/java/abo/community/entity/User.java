package abo.community.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author abo
 * @date 2020/2/10 9:28
 * @remarks
 **/
@Data
@TableName(value = "user")
public class User {
    @TableId(value = "id")
    private Integer id;
    private String name;
    @TableField(value = "account_id")
    private String accountId;
    private String token;
    @TableField(value = "gmt_create")
    private Long gmtCreate;
    @TableField(value = "gmt_modified")
    private Long gmtModified;
    private String bio;
    @TableField(value = "avatar_url")
    private String avatarUrl;

}
