package com.example.demo.controller;

import com.example.demo.model.InputMessage;
import com.example.demo.model.OutputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/envio")
//    @SendTo("/tema/mensajes")
//    public OutputMessage envio(InputMessage mensaje) {
//        return new OutputMessage(mensaje.getFrom(), mensaje.getText());
//    }

    @MessageMapping("/envio")
    public void sendPositions(@Payload InputMessage message){
        System.out.println("entro por aca");

        simpMessagingTemplate.convertAndSend("/tema/mensajes", new OutputMessage(message.getFrom(), message.getText()) );
    }

}