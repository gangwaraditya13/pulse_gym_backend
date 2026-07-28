package com.pluse.gym_admin.Dto.HallOfFameDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHallOfFameDto {

    @NonNull
    private String championName;
    @NonNull
    private String championship;

    private String ImageUrl;

    private String publicId;
}
