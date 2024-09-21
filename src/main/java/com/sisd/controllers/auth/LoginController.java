package com.sisd.controllers.auth;

import com.sisd.dtostudent.GenericResponse;
import com.sisd.dtostudent.auth.LoginRequestDto;
import com.sisd.dtostudent.auth.LoginResponseDto;
import com.sisd.service.auth.LoginService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@Tag(name= "student")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody LoginRequestDto loginRequestDto){
         try{
            LoginResponseDto response = loginService.login(loginRequestDto);
            return ResponseEntity.ok()
            .body(GenericResponse.success(response,
        "Successfully login"));
        }catch(ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode())
            .body(GenericResponse.eror(e.getReason()));
        }catch(Exception e){
            return ResponseEntity.internalServerError()
            .body(GenericResponse.eror(e.getMessage()));

        }

    }
    }

