package com.pluse.gym_admin.Dto.SpecializedTrainingDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestSpecializedTrainingDto {
    @NonNull
    private String programName;
    @NonNull
    private String programDescription;
}
