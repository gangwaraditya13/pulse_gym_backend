package com.pluse.gym_admin.Dto.SpecializedTrainingDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSpecializedTrainingDto {
    @NotBlank
    private String programName;
    @NotBlank
    private String programDescription;
}
