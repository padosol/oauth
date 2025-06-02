package com.ecommerce.userservice.user.controller;

import com.ecommerce.userservice.user.util.SocialLoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RestController
public class OauthController {

    @GetMapping("/{socialLoginType}")
    public void socialLoginType(
            @PathVariable("socialLoginType") SocialLoginType socialLoginType
    ) {
        log.info("로그인 요청 {}", socialLoginType);
    }

    @GetMapping("/{socialLoginType}/callback")
    public String callback(
            @PathVariable("socialLoginType") SocialLoginType socialLoginType,
            @RequestParam("code") String code
    ) {
        log.info("== 로그인 인증 코드: {},  로그인 타입: {}", code, socialLoginType);
        return "code";
    }

}
