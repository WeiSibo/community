package abo.community.service;

import abo.community.dto.CommentGetDTO;
import abo.community.entity.Comment;
import abo.community.entity.Post;
import abo.community.entity.User;
import abo.community.enums.CommentTypeEnum;
import abo.community.exception.CustomizeErrorCode;
import abo.community.exception.CustomizeException;
import abo.community.mapper.CommentMapper;
import abo.community.mapper.PostMapper;
import abo.community.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author abo
 * @date 2020/3/4 10:04
 * @remarks
 **/
@Service
@Transactional
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private PostMapper postMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            //回复评论
            Comment dbCommment = commentMapper.selectById(comment.getParentId());
            if(dbCommment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else{
            //回复帖子
            Post post = postMapper.selectById(comment.getParentId());
            if(post == null){
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            commentMapper.insert(comment);
            postMapper.updateCommentCount(post.getId());
        }
    }

    public List<CommentGetDTO> listByTargetId(Integer id, CommentTypeEnum type){
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        queryWrapper.eq("type", type.getType());
        queryWrapper.orderByDesc("gmt_create");
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        if(comments.size() == 0){
            return new ArrayList<>();
        }
        //获得去重的评论人
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //获取评论人并转成map
        List<User> users = userMapper.selectBatchIds(commentators);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment为commentDTO
        List<CommentGetDTO> commentDTOs = comments.stream().map(comment -> {
            CommentGetDTO commentDTO = new CommentGetDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOs;
    }
}
