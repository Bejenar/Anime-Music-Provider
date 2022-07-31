package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.converter;

import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.model.music.Music;
import holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto.AnimeMusicDto;
import holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto.SearchAnimeResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SearchAnimeResponseDtoToAnimeMusicConverter implements Converter<SearchAnimeResponseDto, AnimeMusic> {

    @Override
    public AnimeMusic convert(SearchAnimeResponseDto source) {
        return AnimeMusic.builder()
            .malID(source.getMalID())
            .title(source.getName())
            .season(resolveSeason(source))
            .music(convertAnimeMusicDtoListToMusicList(source.getThemes()))
            .build();
    }

    private List<Music> convertAnimeMusicDtoListToMusicList(final List<AnimeMusicDto> dtoList) {
        return dtoList.stream().map(theme -> Music.builder()
            .title(theme.getThemeName())
            .url(theme.getMirror().getMirrorURL())
            .type(theme.getThemeType())
            .notes(theme.getMirror().getNotes())
            .build())
            .collect(Collectors.toList());
    }

    private String resolveSeason(final SearchAnimeResponseDto dto) {
        return Stream.of(dto.getSeason(), dto.getYear().toString())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
    }
}
