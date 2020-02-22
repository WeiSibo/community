package abo.community.controller;

import abo.community.dto.PaginationDTO;
import abo.community.dto.PostDTO;
import abo.community.entity.Post;
import abo.community.entity.User;
import abo.community.mapper.PostMapper;
import abo.community.mapper.UserMapper;
import abo.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "6") Integer size){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PaginationDTO paginationDTO = postService.list(page,size);
        model.addAttribute("pagination",paginationDTO);
        return "index";
    }
}
