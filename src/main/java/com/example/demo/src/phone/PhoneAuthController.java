package com.example.demo.src.phone;

import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.phone.model.PostPhoneAuthReq;
import com.example.demo.utils.ValidationRegex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/phoneAuth") @RequiredArgsConstructor
public class PhoneAuthController {

    private final PhoneAuthService phoneAuthService;

    @GetMapping("/request")
    public BaseResponse<?> getAuthCode(@RequestParam String phoneNumber){
        if(phoneAuthService.isNumberAuthenticated(phoneNumber))
            return new BaseResponse<>(BaseResponseStatus.PHONENUMBER_ALREADY_AUTHENTICATED);
        if(!ValidationRegex.isRegexPhoneNumber(phoneNumber))
            return new BaseResponse<>(BaseResponseStatus.PHONENUMBER_INVALID);
        Long reqId = phoneAuthService.createPhoneAuthRequest(phoneNumber);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/accept")
    public BaseResponse<?> createAuthNumbers(@RequestBody PostPhoneAuthReq req){
        if(phoneAuthService.doAuth(req))
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        return new BaseResponse<>(BaseResponseStatus.PHONENUMBER_AUTH_FAILED);
    }

    @GetMapping("/{phoneNumber}")
    public BaseResponse<?> isAuthenticated(@PathVariable String phoneNumber){
        try {
            if (phoneAuthService.isNumberAuthenticated(phoneNumber))
                return new BaseResponse<>(BaseResponseStatus.SUCCESS);
            return new BaseResponse<>(BaseResponseStatus.PHONENUMBER_AUTH_FAILED);
        }
        catch(Exception e) {
            return new BaseResponse<>(BaseResponseStatus.PHONENUMBER_AUTH_FAILED);
        }
    }

    @DeleteMapping("/{phoneNumber}")
    public BaseResponse<?> deleteNumberAuth(@PathVariable String phoneNumber){
       if( phoneAuthService.deleteNumberAuth(phoneNumber))
           return new BaseResponse<>(BaseResponseStatus.SUCCESS);
       return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);
    }
}
