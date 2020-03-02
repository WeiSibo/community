package abo.community.service;

import abo.community.dto.PaginationDTO;
import abo.community.dto.PostDTO;
import abo.community.entity.Post;
import abo.community.entity.User;
import abo.community.mapper.PostMapper;
import abo.community.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public PaginationDTO list(Integer page, Integer size) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        Page<Post> myPage = new Page<>(page, size);
        IPage<Post> iPage = postMapper.selectPage(myPage, queryWrapper);

        List<Post> posts = iPage.getRecords();
        List<PostDTO> postDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for(Post post: posts){
            User user = userMapper.findById(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        paginationDTO.setPosts(postDTOList);
        Long total = iPage.getPages();
        int totalPage = total.intValue();
        paginationDTO.setPagination(Integer.valueOf(totalPage), page, size);
        return paginationDTO;
    }

    public PaginationDTO list(Integer id, Integer page, Integer size) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", id);
        Page<Post> myPage = new Page<>(page, size);
        IPage<Post> iPage = postMapper.selectPage(myPage, queryWrapper);

        List<Post> posts = iPage.getRecords();
        List<PostDTO> postDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for(Post post: posts){
            User user = userMapper.findById(post.getCreator());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }
        paginationDTO.setPosts(postDTOList);
        Long total = iPage.getPages();
        int totalPage = total.intValue();
        paginationDTO.setPagination(Integer.valueOf(totalPage), page, size);
        return paginationDTO;
    }

    public PostDTO getById(Integer id){
        Post post = postMapper.selectById(id);
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        User user = userMapper.selectById(post.getCreator());
        postDTO.setUser(user);
        return postDTO;
    }

    public void createOrUpdate(Post post) {
        if(post.getId() == null){
            post.setGmtCreate(System.currentTimeMillis());
            post.setGmtModified(post.getGmtCreate());
            postMapper.insert(post);
        }else{
            post.setGmtModified(System.currentTimeMillis());
            postMapper.updateById(post);
        }
    }
}
