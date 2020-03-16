package abo.community.controller;

import abo.community.dto.CommentGetDTO;
import abo.community.dto.PostDTO;
import abo.community.service.CommentService;
import abo.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author abo
 * @date 2020/3/1 15:46
 * @remarks
 **/
@Controller
public class PostController {

    @Autowired(required = false)
    private PostService postService;

    @Autowired(required = false)
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") Integer id,
                       Model model){
        PostDTO postDTO = postService.getById(id);
        List<CommentGetDTO> commentsDTO = commentService.listByPostId(id);
        postService.incView(id);
        model.addAttribute("post", postDTO);
        model.addAttribute("comments", commentsDTO);
        return "post";
    }
}
