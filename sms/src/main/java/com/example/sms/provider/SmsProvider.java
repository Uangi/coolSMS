package com.example.sms.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Component
public class SmsProvider {
    private final DefaultMessageService messageService;

    @Value("${sms.from-number}")String FROM;

    String to ="01085526671";

    public SmsProvider(
        @Value("${sms.api-key}")String API_KEY,
        @Value("${sms.api-secret-key}")String API_SECRET_KEY,
        @Value("${sms.api-domain}") String DOMAIN) {
        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, DOMAIN);
    }


    /**
     * 단일 메시지 발송 예제
     */
    public boolean sendSms(String to) {

         Message message = new Message();
        message.setFrom(FROM);
        message.setTo(to);
        // message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가");
        message.setText("문자 내용 " ); // 전해질 문자 내용 작성

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
        String statusCode = response.getStatusCode();
        boolean result = statusCode.equals("2000");

        return result;
    }

}
