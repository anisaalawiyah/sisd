package com.sisd.service.auth;

import com.sisd.dtostudent.auth.LoginRequestDto;
import com.sisd.dtostudent.auth.LoginResponseDto;
import com.sisd.entity.Users;
import com.sisd.repository.UsersRepository;
import com.sisd.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginServiceImpl implements LoginService{
    
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Users user = usersRepository
                .findByUserName(loginRequestDto.getUsername())
                .orElse(null);
        if (user != null) {
            boolean isMatch = passwordEncoder.matches(loginRequestDto.getPassword(),
                    user.getPassword());
            if (isMatch) {
                LoginResponseDto loginResponseDto = new LoginResponseDto();
                loginResponseDto.setUsername(user.getUserName());
                loginResponseDto.setRole(user.getRoles().getRoleName());
                loginResponseDto.setToken(jwtUtil.generateToken(user));
                return loginResponseDto;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
        "invalid usename or password");
    }
}
