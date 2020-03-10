package abo.community.service;

import abo.community.entity.Comment;
import abo.community.exception.CustomizeErrorCode;
import abo.community.exception.CustomizeException;
import abo.community.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author abo
 * @date 2020/3/4 10:04
 * @remarks
 **/
@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
