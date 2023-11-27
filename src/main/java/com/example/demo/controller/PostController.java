package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.service.PostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;

import com.example.demo.entity.Post;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/api/posts/add")
    public String Menu() {
        return "startpage"; // layout 폴더 안에 common.html 파일이 있는 경우
    }
    

    // PostController.java
    @GetMapping("/main")
    public String postList(Model model) {
    model.addAttribute("list", postService.getAllPosts());
    return "boardwrite";
}
    
    @PostMapping("/add")
public String addPost(@RequestParam("content") String content,
                      @RequestParam("imageFile") MultipartFile imageFile,
                      HttpSession session,
                      RedirectAttributes redirectAttributes,
                      Model model) {
    // 전달된 데이터 로그로 출력
    logger.info("Received request with content: {}", content);

    try {
        // 파일 업로드 및 저장
        byte[] imageBytes = imageFile.getBytes();

        // Get the currently authenticated user's username using session
        String username = (String) session.getAttribute("username");

        // 생성자를 통해 Post 객체를 생성하면서 사용자 이름을 설정
        Post post = new Post(content, imageBytes, username);
        postService.write(post);

        // 모델에 데이터 추가
        model.addAttribute("content", content);
        model.addAttribute("username", username);

        return "r";
    } catch (IOException e) {
        // 파일 업로드 실패 시 예외 처리
        logger.error("Error uploading file: {}", e.getMessage());
        redirectAttributes.addFlashAttribute("error", "파일 업로드에 실패했습니다.");
        return "redirect:/main"; // or wherever you want to redirect in case of an error
    }
}

}