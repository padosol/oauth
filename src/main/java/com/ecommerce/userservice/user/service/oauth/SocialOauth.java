package com.ecommerce.userservice.user.service.oauth;

import com.ecommerce.userservice.user.util.SocialLoginType;

public interface SocialOauth {

    String getOauthRedirectURL();
    String requestAccessToken(String code);

    default SocialLoginType type() {
        if (this instanceof GoogleOauth) {
            return SocialLoginType.GOOGLE;
        } else {
            return null;
        }
    }
}
