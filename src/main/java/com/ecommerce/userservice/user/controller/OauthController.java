package com.ecommerce.userservice.user.controller;

import com.ecommerce.userservice.user.controller.response.UserResponse;
import com.ecommerce.userservice.user.entity.User;
import com.ecommerce.userservice.user.service.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @GetMapping("/users")
    public UserResponse users(@AuthenticationPrincipal PrincipalDetails userDetails) {
        User user = userDetails.getUser();
        return new UserResponse(
                user.getEmail(),
                user.getName(),
                user.getRole().getKey()
        );
    }
}
