package com.example.demo.src.oauth;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.oauth.model.PostUserOAuthRes;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private OAuthService oAuthService;

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    //카카오 로그인 화면 url : kauth.kakao.com/oauth/authorize?client_id=7ecea2fa6adb374c8b21c92c9447219d&redirect_uri=http://localhost:9000/oauth/kakao&response_type=code
    //원래는 클라이언트 단에서 카카오로 부터 코드를 받고 이를 다시 카카오에게 전달하여 access token을 받은 뒤 서버에게 전달하고 서버가 이로부터 회원 정보를 얻어 회원가입 또는 로그인을 진행하는데
    //현재는 백엔드에서 위 모든 과정을 진행하였다.(클라이언트 측에서 소셜로그인 기능 구현 여부 미정 이슈로 인해)
    @ResponseBody
    @GetMapping("/kakao")
    public BaseResponse<PostUserOAuthRes> kakaoSingup(@RequestParam String code) {
        try{
            System.out.println("code = " + code);
            String accessToken = oAuthService.getKakaoAccessToken(code); //코드로 부터 access token 추출
            System.out.println("accessToken = " + accessToken);
            PostUserOAuthRes postUserOAuthRes = oAuthService.createKakaoUser(accessToken);  //access token에서 사용자 정보 추출
            return new BaseResponse<>(postUserOAuthRes);
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }
}
