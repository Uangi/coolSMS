package com.example.sms.service.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sms.provider.SmsProvider;
import com.example.sms.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceimplementation implements TestService {

    private final SmsProvider smsProvider;

    @Override
    public ResponseEntity<String>sendSms(String to) {

        try {
            boolean result = smsProvider.sendSms(to);
            if(!result) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메세지 전송 실패");
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메세지 전송 중 예외 발생");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메세지 전송 성공");
    }
}
