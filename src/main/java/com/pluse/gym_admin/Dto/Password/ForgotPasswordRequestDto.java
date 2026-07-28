package com.pluse.gym_admin.Dto.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
}
