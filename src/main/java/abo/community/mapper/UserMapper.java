package abo.community.mapper;

import abo.community.dto.GithubUser;
import abo.community.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author abo
 * @date 2020/2/10 9:21
 * @remarks
 **/
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(@Param("token") String token);
}
