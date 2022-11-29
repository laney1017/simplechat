package com.example.simplechat.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    // 1. 주소입력후 바로가는 페이지는 index.html 입니다.
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 2. index.html에서 닉네임을 입력후 채팅을 시작합니다.  GET방식의 /goMain 주소를 입력하고, nickName(String)이라는 변수를 서버에 보냅니다.
    // nickName은 세션에 nickName이라는 이름으로 등록해주세요. 단, 중복이나 빈값이라면 index.html 페이지로 돌려보내주세요.
    @GetMapping("/goMain")
    public String goMain(String nickName, HttpSession session) {
        session.setAttribute("nickName", nickName);
        return "Main";
    }
}