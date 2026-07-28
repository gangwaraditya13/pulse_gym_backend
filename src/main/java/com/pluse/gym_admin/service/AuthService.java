package com.pluse.gym_admin.service;

import com.pluse.gym_admin.Dto.Password.ForgotPasswordRequestDto;
import com.pluse.gym_admin.Dto.Password.ResetPasswordRequestDto;
import com.pluse.gym_admin.Dto.Tokens.TokenResponseDto;
import com.pluse.gym_admin.Dto.UserDto.UserLoginRequestDto;

public interface AuthService {

    void forgetPassword(ForgotPasswordRequestDto forgotPasswordRequestDto);

    void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);

    TokenResponseDto loginToken(UserLoginRequestDto userLoginRequestDto);
}
