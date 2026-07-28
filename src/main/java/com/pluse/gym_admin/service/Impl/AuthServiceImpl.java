package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.Dto.Password.ForgotPasswordRequestDto;
import com.pluse.gym_admin.Dto.Password.ResetPasswordRequestDto;
import com.pluse.gym_admin.Dto.Tokens.TokenResponseDto;
import com.pluse.gym_admin.Dto.UserDto.UserLoginRequestDto;
import com.pluse.gym_admin.entity.OtpToken;
import com.pluse.gym_admin.entity.User;
import com.pluse.gym_admin.repository.OtpTokenRepository;
import com.pluse.gym_admin.repository.UserRepository;
import com.pluse.gym_admin.security.JwtUtil;
import com.pluse.gym_admin.service.AuthService;
import com.pluse.gym_admin.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpTokenRepository otpTokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder PASSWORD_ENCODER;

    @Override
    public void forgetPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) {

        User user = userRepository.findByEmail(forgotPasswordRequestDto.getEmail()).orElseThrow();

        boolean userExists = userRepository.existsByEmail(forgotPasswordRequestDto.getEmail());
        String email = forgotPasswordRequestDto.getEmail();

        if (!userExists) {
            throw new RuntimeException("User not found with email: " + email);
        }

        String otp = String.format("%06d", new Random().nextInt(999999));

        otpTokenRepository.deleteByUserName(user.getUserName());

        OtpToken otpToken = OtpToken.builder()
                .userName(user.getUserName())
                .otp(otp)
                .expiryDate(LocalDateTime.now().plusMinutes(5))
                .build();

        otpTokenRepository.save(otpToken);
        emailService.sendOtpEmail(forgotPasswordRequestDto.getEmail(),otp);
    }

    @Override
    public void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        String userName = resetPasswordRequestDto.getUserName();
        String otp = resetPasswordRequestDto.getOtp();
        String newPassword = resetPasswordRequestDto.getNewPassword();

        OtpToken otpToken = otpTokenRepository.findByUserName(userName).orElseThrow();

        if (otpToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            otpTokenRepository.delete(otpToken);
            throw new RuntimeException("OTP has expired");
        }

        if(!otpToken.getOtp().equals(resetPasswordRequestDto.getOtp())){
            throw new RuntimeException("Invalid OTP");
        }

        String encodedPassword = PASSWORD_ENCODER.encode(newPassword);

        User user = userRepository.findByUserName(userName).orElse(null);
        if(user!=null) {
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }

        otpTokenRepository.delete(otpToken);

    }

    public TokenResponseDto loginToken(UserLoginRequestDto userLoginRequestDto){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginRequestDto.getUserName(),userLoginRequestDto.getPassword()));
            UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(userLoginRequestDto.getUserName());
            String jwtToken = jwtUtil.generateAccessToken(userDetails.getUsername());
            TokenResponseDto tokenResponseDto = new TokenResponseDto();
            tokenResponseDto.setJwtToken(jwtToken);
            return tokenResponseDto;
        } catch (BadCredentialsException e){
            throw new RuntimeException("Invalid email or password");
        }

    }
}
