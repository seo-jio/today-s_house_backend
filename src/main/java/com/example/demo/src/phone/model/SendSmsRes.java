package com.example.demo.src.phone.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data @AllArgsConstructor @NoArgsConstructor
public class SendSmsRes {
    private String statusCode;
    private String statusName;
    private String requestId;
    private Timestamp requestTime;
}
