    package com.example.demo.controller;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    import com.example.demo.entity.Board; // 필요하다면 사용하세요
    import com.example.demo.service.BoardService;
    import com.example.demo.service.PostService;
    import com.example.demo.service.PostServiceImpl;
    import com.example.demo.entity.UserBoard;
    import com.example.demo.exception.DuplicateIdOrUsernameException;
    import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

    @Controller
    public class BoradController {

        @Autowired
        private BoardService boardService;
        @Autowired
        private UserService userService;
        @Autowired
        private final PostServiceImpl postService;
    
        public BoradController(PostServiceImpl postService) {
        this.postService = postService;
        }
    
        @GetMapping("/board/write")
        public String boardWriteForm() {
            return "board";
        }

        @GetMapping("/board/write/sign")
        public String boardSign() {

            return "sign"; // sign.html 파일을 반환하도록 설정
        }

       

        @GetMapping("/main")
        public String postList(Model model) {
            model.addAttribute("list", postService.getAllPosts());
            return "startpage";
        }
        @GetMapping("/home")
        public String h(Model model) {
            model.addAttribute("list", postService.getAllPosts());
            return "startpage";
        }
        @GetMapping("/board/page")  // 슬래시를 제거해주세요
        public String writePage() {
            return "main";  // "main"은 Thymeleaf 템플릿 이름을 가정한 것입니다
        }
        @GetMapping("/hospital")
        public String mapPage() {
            return "map";  // "main"은 Thymeleaf 템플릿 이름을 가정한 것입니다
        }
        @GetMapping("/item")
        public String itemPage() {
            return "item";  // "main"은 Thymeleaf 템플릿 이름을 가정한 것입니다
        }
        @PostMapping("/board/writepro")
        public String boardLoginPro(@RequestParam int ID, @RequestParam String PW, HttpSession session, RedirectAttributes redirectAttributes) {
            boolean isAuthenticated = userService.authenticateUser(ID, PW);
    
            if (isAuthenticated) {
                session.setAttribute("username", userService.getUsernameById(ID));
                redirectAttributes.addAttribute("ID", ID);
                
                return "loginS";
            } else {
                return "redirect:/loginError";
            }
        }
    
        @PostMapping("/board/write/signpro")
        public String UserWritePro(UserBoard user, RedirectAttributes redirectAttributes) {
            try {
                userService.write(user);
                return "singSuccess";
            } catch (DuplicateIdOrUsernameException e) {
                return "signError";
            }
        }
    }
