package com.welab.k8s_backend_user.api.open;

import com.welab.k8s_backend_user.common.dto.ApiResponseDto;
import com.welab.k8s_backend_user.domain.dto.SiteUserLoginDto;
import com.welab.k8s_backend_user.domain.dto.SiteUserRefreshDto;
import com.welab.k8s_backend_user.domain.repository.dto.SiteUserRegisterDto;
import com.welab.k8s_backend_user.secret.jwt.dto.TokenDto;
import com.welab.k8s_backend_user.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/user/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserAuthController {
    private final SiteUserService siteUserService;

    @PostMapping(value = "/register")
    public ApiResponseDto<String> register(@RequestBody @Valid SiteUserRegisterDto registerDto) {
        siteUserService.registerUser(registerDto);
        return ApiResponseDto.defaultOk();
    }
    @PostMapping(value = "/login")
    public ApiResponseDto<TokenDto.AccessRefreshToken> login(@RequestBody @Valid SiteUserLoginDto loginDto) {
        TokenDto.AccessRefreshToken token = siteUserService.login(loginDto);
        return ApiResponseDto.createOk(token);
    }
    @PostMapping(value = "/refresh")
    public ApiResponseDto<TokenDto.AccessToken> refresh(@RequestBody @Valid SiteUserRefreshDto refreshDto) {
        TokenDto.AccessToken token = siteUserService.refresh(refreshDto);
        return ApiResponseDto.createOk(token);
    }
}