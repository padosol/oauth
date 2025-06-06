# Spring Boot OAuth2 Example

이 프로젝트는 **Spring Boot**와 **Spring Security**를 활용하여 OAuth2 인증을 구현한 예제입니다. 구글, 네이버, 카카오 등 다양한 소셜 로그인 연동이 가능합니다.

## 주요 기능

- OAuth2 기반 소셜 로그인 (Google, Naver, Kakao 등)
- Spring Security를 통한 인증/인가 처리

## 사용 기술
- Java 21
- Spring Boot
- Spring Security
- Spring Security OAuth2 Client

## 실행 방법

1. **환경 변수 설정**
   - `application.yml` 에 각 소셜 로그인 클라이언트 ID / Secret을 등록합니다.
   - 예시:
     ```yaml
     spring:
       security:
         oauth2:
           client:
             registration:
               google:
                 client-id: ${CLIENT_ID}
                 client-secret: ${CLIENT_SECRET}
               # 네이버, 카카오 등 추가
     ```

2. **프로젝트 빌드 및 실행**
   - Gradle:  
     ```bash
     ./gradlew bootRun
     ```
   또는 인텔리제이를 사용하여 실행

3. **테스트**
   - 브라우저에서 `http://localhost:8080` 접속 후 소셜 로그인 버튼을 클릭하여 인증을 진행합니다.
   - `http://localhost:8080/h2-console` 을 통해 데이터베이스에 접속 가능

4. **결과**
    - `http://localhost:8080/users` 엔드포인트는 권한이 없으므로 `http://localhost:8080/login` 으로 리다이렉트 된다.
    - 로그인 후 `http://localhost:8080/users` 접근가능
    - 현재 프로젝트는 Session 을 통해 유저를 관리중입니다.
