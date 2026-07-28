package com.pluse.gym_admin.Dto.ClientQueryMessageDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestClientQueryMessageDto {
    @NonNull
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String message;
}
