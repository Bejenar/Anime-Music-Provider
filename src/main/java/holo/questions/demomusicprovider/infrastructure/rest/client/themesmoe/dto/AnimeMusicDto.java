package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnimeMusicDto {

    private String themeType;
    private String themeName;
    private MirrorDto mirror;
}
