package com.example.demo.src.phone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class SendSmsReq {
    private String from;
    private String type;
    private String content;
    private List<Message> messages;

    public SendSmsReq(String from, String to, String content){
        this.from = from;
        this.type = "SMS";
        this.content = "라이징테스트";
        messages = new ArrayList<>();
        messages.add(new Message(to, content));
    }

    @AllArgsConstructor @Data
    class Message{
        String to;
        String content;
    }
}
