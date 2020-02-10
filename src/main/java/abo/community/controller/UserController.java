package abo.community.controller;

import abo.community.dto.GithubUser;
import abo.community.entity.User;
import abo.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author abo
 * @date 2020/2/10 9:22
 * @remarks
 **/
@RestController
public class UserController {

    @Autowired(required = false)
    UserMapper userMapper;

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Long id){
        return userMapper.selectById(id);
    }

    @PostMapping("/insert")
    public void insert(@RequestBody User user){
        userMapper.insert(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userMapper.updateById(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userMapper.deleteById(id);
    }
}
