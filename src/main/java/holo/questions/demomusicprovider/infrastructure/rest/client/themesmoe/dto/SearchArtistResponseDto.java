package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchArtistResponseDto {

    private String artistID;
    private String artistName;
    private List<SearchAnimeResponseDto> themes;
}
