package com.pluse.gym_admin.Dto.UserDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {

    @NotBlank
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    private String about;

    @NotBlank
    private String password;

    private String imageUrl;

    private String publicId;
}