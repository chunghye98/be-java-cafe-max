package kr.codesqaud.cafe.post.controller;

import kr.codesqaud.cafe.post.domain.Post;
import kr.codesqaud.cafe.post.service.AuthService;
import kr.codesqaud.cafe.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;


@Controller
public class PostController {
    private final PostService postService;
    private final AuthService authService;

    @Autowired
    public PostController(PostService postService, AuthService authService, HttpSession httpSession) {
        this.postService = postService;
        this.authService = authService;

    }

    @GetMapping("/posts/form")
    public String form() {
        return "post/form";
    }

    @GetMapping("/posts/{index}")
    public String show(@PathVariable long index, Model model,HttpSession httpSession) throws AccessDeniedException {
        authService.checkLogin(httpSession);
        model.addAttribute("post", postService.findById(index));
        return "post/show";
    }

    @PostMapping("/posts/form")
    public String write(PostCreateRequest postCreateRequest) {
        Post post = new Post.Builder()
                .title(postCreateRequest.getTitle())
                .contents(postCreateRequest.getContents())
                .writer(postCreateRequest.getWriter())
                .build();
        postService.create(post);
        return "redirect:/";
    }
}

