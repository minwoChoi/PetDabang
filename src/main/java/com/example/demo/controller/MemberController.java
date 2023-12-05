package com.example.demo.controller;

import com.example.demo.service.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.example.demo.entity.*;
import com.example.demo.repository.MemberRepository;
import java.util.*;
import java.security.Principal;
@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

     @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DentistService dentistService;

    @GetMapping("/sign")
    public String showSignUpForm() {
       return "signUp";
    }

    @GetMapping("/login")
    public String showLoginForm() {
       return "Login";
    }

    @GetMapping("/Login")
    public String showLogForm() {
       return "Login";
    }
    
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute MemberEntity member, Model model) {
        try {
            if (!memberService.isAnyDuplicate(member)) {
                memberService.registerMember(member);
                return "Login"; // Successful registration redirects to the login page
            } else {
                if (memberService.isDuplicateById(member)) {
                    return "OverlapId";
                }  else if (memberService.isDuplicateByEmail(member)) {
                    return "OverlapEMAIL";
                }   else if (memberService.isDuplicateByRNN(member)) {
                    return "OverlapRNN";
                } 
                else {
                    return "redirect:/errorPage";
                }
            }
        } catch (Exception e) {
            model.addAttribute("errorType", "OTHER");
            model.addAttribute("error", "An error occurred during registration.");
            return "redirect:/errorPage";
        }
    }
    @GetMapping("/")
public String showMainPage(Model model) {
    // 데이터베이스에서 모든 치과 정보를 가져옵니다
    List<DentistEntity> allDentists = dentistService.getAllDentists();

    // 가져온 정보를 모델에 추가합니다
    model.addAttribute("allDentists", allDentists);

    // Main.html 페이지를 반환합니다
    return "Main";
}


    @PostMapping("/authenticate")
public String processLogin(Model model, String id, String pw, HttpSession session) {
    if (memberService.authenticateUser(id, pw)) {
        MemberEntity loggedInUser = memberService.getMemberById(id);
        session.setAttribute("loggedInUser", loggedInUser); // 세션에 사용자 정보 저장
        model.addAttribute("member", loggedInUser);
        System.out.println("Login Successful for User ID: " + id);
        return "loginS";
    } else {
        model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
        return "login";
    }
}   

    @GetMapping("/Profile")
    public String showProfile(Model model, HttpSession session) {
        MemberEntity loggedInUser = (MemberEntity) session.getAttribute("loggedInUser"); // 세션에서 사용자 정보 가져오기
        if (loggedInUser != null) {
        model.addAttribute("member", loggedInUser);
        System.out.println("Found User: " + loggedInUser.toString()); // 디버깅용 출력
        // 여기에서 "/Profile"로 변경하여 Profile 페이지로 이동하도록 설정
        return "Profile";
    } else {
        System.out.println("User Not Found"); // 디버깅용 출력
        return "redirect:/errorPage";
        }
    }

    // 회원가입 정보 저장
    @GetMapping("/searchDentists")
public String searchDentists(@ModelAttribute DentistEntity searchCriteria, Model model) {
    List<DentistEntity> searchResults = dentistService.getAllDentists();
    
    model.addAttribute("allDentists", searchResults); // 수정된 부분
    return "Search";
}

    
    
}
