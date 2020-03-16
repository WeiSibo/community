package abo.community.mapper;

import abo.community.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author abo
 * @date 2020/2/18 18:14
 * @remarks
 **/
public interface PostMapper extends BaseMapper<Post> {

    @Select("SELECT * FROM post")
    List<Post> list();
    @Update("UPDATE post SET view_count = view_count + 1 where id = #{id}")
    void updateViewCount(@Param(value = "id") Integer id);
    @Update("UPDATE post SET comment_count = comment_count + 1 where id = #{id}")
    void updateCommentCount(@Param(value = "id") Integer id);
}
