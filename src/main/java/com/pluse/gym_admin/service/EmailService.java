package com.pluse.gym_admin.service;

public interface EmailService {
    void sendOtpEmail(String toEmail, String otp);
}
