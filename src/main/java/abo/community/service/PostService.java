package abo.community.service;

import abo.community.dto.PostDTO;
import abo.community.entity.Post;
import abo.community.entity.User;
import abo.community.mapper.PostMapper;
import abo.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abo
 * @date 2020/2/19 21:51
 * @remarks
 **/
@Service
public class PostService {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private PostMapper postMapper;

    public List<PostDTO> list() {
        List<Post> posts = postMapper.list();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: posts){
            User user = userMapper.findById(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }

}
