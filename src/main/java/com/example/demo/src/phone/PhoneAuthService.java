package com.example.demo.src.phone;

import com.example.demo.src.phone.model.PhoneAuth;
import com.example.demo.src.phone.model.PostPhoneAuthReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Slf4j
public class PhoneAuthService {

    private final NaverSensService naverSensService;
    private final PhoneAuthDao phoneAuthDao;

    public boolean isNumberAuthenticated(String phoneNumber) {
        return phoneAuthDao.isAuthenticated(phoneNumber);
    }

    @Transactional
    public Long createPhoneAuthRequest(String phoneNumber) {
        int max = 99999;
        int min = 10000;
        Integer code = (int)Math.floor(Math.random()*(max-min+1)+min);
        Long id = phoneAuthDao.createRequest(phoneNumber, code.toString());
        naverSensService.sendMessage(phoneNumber, "당신의 인증코드는 ["+code.toString()+"] 입니다.");
        return id;
    }

    @Transactional
    public boolean doAuth(PostPhoneAuthReq req) {
        if(isNumberAuthenticated(req.getPhoneNumber()))
            return true;
        if(!phoneAuthDao.isExist(req.getPhoneNumber()))
            return false;
        PhoneAuth phoneAuth = phoneAuthDao.findByPhoneNumber(req.getPhoneNumber());
        if(phoneAuth.getCode().equals(req.getCode())) {
            phoneAuthDao.doAuthenticate(phoneAuth.getPhoneNumber());
            return true;
        }
        return true;
    }

}
