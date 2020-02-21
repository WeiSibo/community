package abo.community.mapper;

import abo.community.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author abo
 * @date 2020/2/18 18:14
 * @remarks
 **/
public interface PostMapper extends BaseMapper<Post> {

    @Select("SELECT * FROM post")
    IPage<Post> list(Page<?> page, Integer state);
}
