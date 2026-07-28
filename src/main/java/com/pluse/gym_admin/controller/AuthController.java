package com.pluse.gym_admin.controller;

import com.pluse.gym_admin.Dto.Password.ForgotPasswordRequestDto;
import com.pluse.gym_admin.Dto.Password.ResetPasswordRequestDto;
import com.pluse.gym_admin.Dto.Tokens.TokenResponseDto;
import com.pluse.gym_admin.Dto.UserDto.RequestUser;
import com.pluse.gym_admin.Dto.UserDto.ResponseUser;
import com.pluse.gym_admin.Dto.UserDto.UserLoginRequestDto;
import com.pluse.gym_admin.service.AuthService;
import com.pluse.gym_admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseUser> signUp(@RequestBody RequestUser requestUser){
        ResponseUser user = userService.createUser(requestUser);

        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequestDto request){

        authService.forgetPassword(request);

        return ResponseEntity.ok("OTP sent successfully");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequestDto request){

        authService.resetPassword(request);

        return ResponseEntity.ok("Password updated");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @RequestBody UserLoginRequestDto userLoginRequestDto){

        return ResponseEntity.ok(authService.loginToken(userLoginRequestDto));
    }

}
