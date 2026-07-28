package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Year;
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendOtpEmail(String toEmail, String otp) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Password Reset Request - Pulse Gym Admin");

            String htmlMsg = "<div style='font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif; max-width: 600px; margin: 0 auto; border: 1px solid #eaeaea; padding: 30px; border-radius: 12px; background-color: #ffffff;'>"
                    + "<div style='text-align: center; margin-bottom: 25px;'>"
                    + "  <h1 style='color: #28a745; margin: 0; font-size: 30px; font-weight: 700; letter-spacing: 1px;'>Pulse Gym</h1>"
                    + "  <p style='margin: 8px 0 0; color: #666; font-size: 15px;'>Admin Portal</p>"
                    + "  <div style='height: 2px; background: linear-gradient(to right, transparent, #28a745, transparent); margin-top: 15px;'></div>"
                    + "</div>"

                    + "<p style='font-size: 16px; color: #444; line-height: 1.6;'>Hello Admin,</p>"

                    + "<p style='font-size: 16px; color: #444; line-height: 1.6;'>We received a request to reset the password for your <strong>Pulse Gym Admin</strong> account.</p>"

                    + "<p style='font-size: 16px; color: #444; line-height: 1.6;'>Use the following One-Time Password (OTP) to continue:</p>"

                    + "<div style='text-align: center; margin: 35px 0;'>"
                    + "  <span style='font-size: 36px; font-weight: 700; letter-spacing: 8px; color: #333; background-color: #f5fff7; padding: 20px 40px; border-radius: 8px; border: 2px solid #28a745; display: inline-block; box-shadow: 0 4px 8px rgba(40,167,69,0.15);'>"
                    + otp
                    + "</span>"
                    + "</div>"

                    + "<p style='font-size: 15px; color: #666; line-height: 1.6;'>"
                    + "This OTP is valid for <strong>5 minutes</strong>. "
                    + "For security reasons, never share this code with anyone, including other staff members."
                    + "</p>"

                    + "<p style='font-size: 15px; color: #666; line-height: 1.6;'>"
                    + "If you did not request this password reset, you can safely ignore this email. "
                    + "Your account will remain secure."
                    + "</p>"

                    + "<hr style='border: none; border-top: 1px solid #eee; margin: 30px 0;' />"

                    + "<p style='font-size: 13px; color: #999; text-align: center; line-height: 1.5;'>"
                    + "This is an automated email from Pulse Gym Admin System. Please do not reply to this message."
                    + "</p>"

                    + "<p style='font-size: 13px; color: #aaa; text-align: center; margin-top: 20px;'>"
                    + "&copy; " + Year.now().getValue() + " Pulse Gym. All rights reserved."
                    + "</p>"
                    + "</div>";

            String plainTextMsg =
                    "Hello Admin,\n\n"
                            + "We received a request to reset the password for your Pulse Gym Admin account.\n\n"
                            + "Your One-Time Password (OTP) is: " + otp + "\n\n"
                            + "This OTP is valid for 5 minutes.\n"
                            + "Do not share this code with anyone.\n\n"
                            + "If you did not request a password reset, you can safely ignore this email.\n\n"
                            + "Regards,\n"
                            + "Pulse Gym Admin Team";

            helper.setText(plainTextMsg, htmlMsg);

            helper.setReplyTo("plusegym@gmail.com");


        }catch (MessagingException e) {
            throw new RuntimeException("Failed to send professional OTP email", e);
        }

    }
}
