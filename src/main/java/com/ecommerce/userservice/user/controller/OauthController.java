package com.ecommerce.userservice.user.controller;

import com.ecommerce.userservice.user.service.OauthService;
import com.ecommerce.userservice.user.util.SocialLoginType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/{socialLoginType}")
    public void socialLoginType(
            @PathVariable("socialLoginType") SocialLoginType socialLoginType,
            HttpServletResponse response
    ) {
        log.info("로그인 요청 {}", socialLoginType);
        String redirectURL = oauthService.createRedirectURL(socialLoginType);

        try {
            response.sendRedirect(redirectURL);
        } catch(Exception e) {
            log.error("Redirect error");
        }
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
