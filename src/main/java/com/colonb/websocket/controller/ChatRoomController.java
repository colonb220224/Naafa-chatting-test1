package com.colonb.websocket.controller;

import com.colonb.websocket.dto.ChatRoom;
import com.colonb.websocket.service.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class ChatRoomController {
    @Autowired
    private ChatRepository chatRepository;

    // 채팅 리스트 화면
    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return
    @GetMapping("/roomlist")
    public String goChatRoom(Model model){

        model.addAttribute("list", chatRepository.findAllRoom());
//        model.addAttribute("user", "hey");
        log.info("SHOW ALL ChatList {}", chatRepository.findAllRoom());
        return "roomlist";
    }

    // 채팅방 생성
    // 채팅방 생성 후 다시 / 로 return
    @PostMapping("/chat/createroom")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
        ChatRoom room = chatRepository.createChatRoom(name);
        log.info("CREATE Chat Room {}", room);
        rttr.addFlashAttribute("name", room);
        return "redirect:/roomlist";
    }

    // 채팅방 입장 화면
    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
    @GetMapping("/chat/room")
    public String roomDetail(Model model, String roomId){

        log.info("roomId {}", roomId);
        model.addAttribute("room", chatRepository.findRoomById(roomId));
        return "chatroom";
    }

}
