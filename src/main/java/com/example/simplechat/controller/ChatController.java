package com.example.simplechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
public class ChatController {
    //4. 프론트엔드에서는 매초 ArrayList<ArrayList<String>> 형태로 채팅 내용을 호출합니다.첫번째는 닉네임,두번째는 메세지, 세번째는 시간으로된 리스트를 리스트에 넣어 보내주세요.
    // 메세지는 한군데에 모아놔야 하고 메세지 입력할때마다 새로 list를 만들지 않고 모아놓기 위해 static 사용
    // 닉네임,내용,시간을 ArrayList로 만들고 이걸 묶어서 보여줘야 하기 때문에 ArrayList<ArrayList<>>
    static ArrayList<ArrayList<String>> msgList = new ArrayList<>();

    @GetMapping("/chat/getMsg") // 입력메시지 받아오기
    public ArrayList<ArrayList<String>> msg() {
        return msgList;
    }
    @GetMapping("/chat/sendMsg") // 받아온 입력메시지를 보내기 (보내기만하고 응답받는게 없기 때문에 void사용)
    public void receiveMsg(HttpSession session, String msg) {
        ArrayList<String> msgContent = new ArrayList<>();

        // 2. nickName(String)이라는 변수를 서버에 보냅니다.
        // 2. nickName은 세션에 nickName이라는 이름으로 등록해주세요. 단, 중복이나 빈값이라면 index.html 페이지로 돌려보내주세요.
        String nickName = String.valueOf(session.getAttribute("nickName"));
        // valueOf 대신에 toString을 사용해도 가능

        // 4. 첫번째는 닉네임,두번째는 메세지, 세번째는 시간으로된 리스트를 리스트에 넣어 보내주세요.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // 시간형식
        LocalDateTime now = LocalDateTime.now();

        msgContent.add(nickName);
        msgContent.add(msg);
        msgContent.add(dtf.format(now));

        msgList.add(msgContent);
    }
}
