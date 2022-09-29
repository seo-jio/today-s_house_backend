package com.example.demo;

import com.example.demo.src.phone.NaverSensService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 스프링 부트 테스트
 */
@SpringBootTest   // 통합 테스트, 스프링부트 어플리케이션 테스트에 필요한 거의 모든 의존성을 제공.
class DemoApplicationTests {

    @Autowired
    NaverSensService naverSensService;
    @Test
    void contextLoads() {
        try {
            System.out.println("result is " + naverSensService.sendMessage("01087406893", "안녕하세요"));
        }
        catch(Exception e){
            e.printStackTrace();

            System.out.println("error occurr");
        }
    }

}
