package com.sisd.service.auth;

import com.sisd.dtostudent.auth.LoginRequestDto;
import com.sisd.dtostudent.auth.LoginResponseDto;

public interface LoginService {
    
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
