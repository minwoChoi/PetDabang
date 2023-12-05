package com.example.demo.controller;

import com.example.demo.entity.DentistEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.entity.ReservationEntity;
import com.example.demo.service.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.*;
@Controller
public class ReservationController {

    @Autowired
    private MemberService memberService; // Member 서비스

    @Autowired
    private DentistService dentistService; // 치과 의사 서비스

    @Autowired
    private ReservationService reservationService; // 예약 서비스

    @GetMapping("/Reservation")
    public String showReservationForm(Model model, HttpSession session) {
        // 세션에서 loggedInUser 가져오기
        MemberEntity loggedInUser = (MemberEntity) session.getAttribute("loggedInUser");
    
        if (loggedInUser != null) {
            // ReservationEntity에 환자 정보 및 loggedInUser 설정
            model.addAttribute("dentists", dentistService.getAllDentists());

            ReservationEntity reservationEntity = new ReservationEntity();
            reservationEntity.setPatientId(loggedInUser.getID()); // 사용자의 ID를 가져와서 설정
            reservationEntity.setPatientName(loggedInUser.getNAME()); // 사용자의 이름을 가져와서 설정
            //reservationEntity.setLoggedInUser(loggedInUser); // 예약 정보에 loggedInUser 설정
    
            // 모델에 ReservationEntity 추가
            model.addAttribute("reservationEntity", reservationEntity);
    
            // 이하 생략
            return "Reservation";
        } else {
            // 로그인이 되어있지 않으면 로그인 페이지로 리다이렉트 또는 다른 처리를 수행
            return "redirect:/login";
        }
    }
    
    
   
    @PostMapping("/makeReservation")
public String makeReservation(@ModelAttribute("reservationEntity") ReservationEntity reservationEntity ,HttpSession session, Model model) {
    // 이미 reservationEntity에 필요한 값들이 포함되어 있음
     MemberEntity loggedInUser = (MemberEntity) session.getAttribute("loggedInUser");
    reservationEntity.setPatientId(loggedInUser.getID());
    reservationEntity.setPatientName(loggedInUser.getNAME());
    reservationEntity.setDISABILITY((loggedInUser.getDISABILITY()));
    reservationService.makeReservation(reservationEntity);
    List<DentistEntity> allDentists = dentistService.getAllDentists();

    // 가져온 정보를 모델에 추가합니다
    model.addAttribute("allDentists", allDentists);
    return "Main";
}

@GetMapping("/List")
public String showReservationDetails(Model model) {
    List<ReservationEntity> reservations = reservationService.getAllReservations();
    model.addAttribute("reservations", reservations);
    return "ReservationList";
}
    // 추가적인 메서드들을 필요에 따라 구현하세요.
}


