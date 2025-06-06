package com.ecommerce.userservice.user.service;

import com.ecommerce.userservice.user.domain.Role;
import com.ecommerce.userservice.user.entity.UserEntity;
import com.ecommerce.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("OAuth2User: {}", oAuth2User);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String loginId = provider + "_" +providerId;
        String email = oAuth2User.getAttribute("email");

        Optional<UserEntity> optionalUserEntity = userRepository.findById(loginId);
        UserEntity userEntity;

        if(optionalUserEntity.isEmpty()) {
            userEntity = UserEntity.builder()
                    .id(loginId)
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .role(Role.USER)
                    .build();
            userRepository.save(userEntity);
        } else {
            userEntity = optionalUserEntity.get();
        }

        return super.loadUser(userRequest);
    }
}
