package com.devi.blog.springboot.web;

import com.devi.blog.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(Model model, @PathVariable long id){

        model.addAttribute("post", postsService.findById(id));

        return "posts-update";
    }
}
