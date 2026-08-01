package com.pluse.gym_admin.Dto.HallOfFameDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHallOfFameDto {

    @NotBlank
    private String championName;
    @NotBlank
    private String championship;

    @com.fasterxml.jackson.annotation.JsonProperty("imageUrl")
    private String ImageUrl;

    private String publicId;
}
