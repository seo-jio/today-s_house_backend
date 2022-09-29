package com.example.demo.src.phone;

import com.example.demo.src.phone.model.SendSmsReq;
import com.example.demo.src.phone.model.SendSmsRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.util.Date;

@Service
@Slf4j
public class NaverSensService {
    @Value("${naver.sender.number}")
    private String from;

    @Value("${naver.api.access-key-id}")
    private String accessKey;

    @Value("${naver.api.secret-key}")
    private String secretKey;

    @Value("${naver.sens.id}")
    private String applicationId;


    @AllArgsConstructor
    private class Headers{
        public String timestamp;
        public String accesskey;
        public String signature;
    }

    private Headers makeSignature() throws Exception {
        Date date = new Date();
        //This method returns the time in millis
        Long timeMilli = date.getTime();
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String timestamp = timeMilli.toString();			// current timestamp (epoch)
        String url = "/sms/v2/services/"+applicationId+"/messages";
        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return new Headers(timestamp, accessKey, encodeBase64String);
    }

    public Boolean sendMessage(String targetPhoneNumber, String message){
        String targetUrl = "https://sens.apigw.ntruss.com/sms/v2/services/"+applicationId+"/messages";

        try {
            Headers signature = makeSignature();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("x-ncp-apigw-timestamp", signature.timestamp);
            httpHeaders.add("x-ncp-iam-access-key", signature.accesskey);
            httpHeaders.add("x-ncp-apigw-signature-v2", signature.signature);

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonBody = objectMapper.writeValueAsString(new SendSmsReq(from, targetPhoneNumber, message));
            HttpEntity<String> body = new HttpEntity<>(jsonBody, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            System.out.println(body);
            SendSmsRes sendSmsRes = restTemplate.postForObject(new URI(targetUrl), body, SendSmsRes.class);
            if(sendSmsRes.getStatusCode().equals("202"))
                log.error("Success");
            else{
                log.warn(sendSmsRes.getStatusName());
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to generate signature for sens service");
            return false;
        }

        return true;
    }
}
