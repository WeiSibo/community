package abo.community.controller;

import abo.community.dto.PostDTO;
import abo.community.entity.Post;
import abo.community.entity.User;
import abo.community.mapper.PostMapper;
import abo.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

/**
 * @author abo
 * @date 2020/2/18 17:07
 * @remarks
 **/
@Controller
public class PublishController {

    @Autowired(required = false)
    private PostMapper postMapper;

    @Autowired
    private PostService postService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        PostDTO post = postService.getById(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("context", post.getContext());
        model.addAttribute("tag", post.getTag());
        model.addAttribute("id", post.getId());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "context",required = false) String context,
                            @RequestParam(value = "tag",required = false) String tag,
                            @RequestParam(value = "id", required = false) Integer id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("context",context);
        model.addAttribute("tag",tag);
        //该放在前端校验
       if(title == null || title == ""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(context == null || context == ""){
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Post post = new Post();
        post.setTitle(title);
        post.setContext(context);
        post.setTag(tag);
        post.setCreator(user.getId());
        post.setId(id);
        postService.createOrUpdate(post);
        return "redirect:/";
    }
}
