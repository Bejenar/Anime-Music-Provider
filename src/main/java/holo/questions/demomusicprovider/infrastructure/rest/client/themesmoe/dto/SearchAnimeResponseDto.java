package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchAnimeResponseDto {
    private Integer malID;
    private String name;
    private Integer year;
    private String season;
    private List<AnimeMusicDto> themes;
}
