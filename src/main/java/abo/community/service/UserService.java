package abo.community.service;

import abo.community.entity.User;
import abo.community.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author abo
 * @date 2020/3/1 18:12
 * @remarks
 **/
@Service
@Transactional
public class UserService {
    @Autowired(required = false)
    UserMapper userMapper;

    public void createOrUpdate(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("account_id", user.getAccountId());
        User temp = userMapper.selectOne(queryWrapper);
        if(temp == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            user.setGmtCreate(temp.getGmtCreate());
            user.setGmtModified(System.currentTimeMillis());
            user.setId(temp.getId());
            userMapper.updateById(user);
        }
    }
}
