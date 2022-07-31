package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe;

import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.ports.Client;
import holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto.SearchAnimeResponseDto;
import holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto.SearchArtistResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetMusicByArtistClient implements Client<String, List<AnimeMusic>> {

    @Value("${themes.moe.client.music-by-artist.url}")
    private final URI url;

    private final RestTemplate restTemplate;

    private final Converter<SearchAnimeResponseDto, AnimeMusic> converter;

    @Override
    public List<AnimeMusic> executeCall(final String name) {
        ResponseEntity<SearchArtistResponseDto> response = restTemplate
            .exchange(toRequestEntity(name), SearchArtistResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return convertResponseToList(response.getBody());
        }

        throw new IllegalStateException("Received get anime music by id response with status " +
            response.getStatusCode() + " and body " + response.getBody());
    }

    private List<AnimeMusic> convertResponseToList(final SearchArtistResponseDto response) {
        return response.getThemes().stream()
            .map(converter::convert)
            .collect(Collectors.toList());
    }

    private RequestEntity<Void> toRequestEntity(final String name) {
        return RequestEntity.get(buildUriWithPathVariable(name)).build();
    }

    private URI buildUriWithPathVariable(final String name) {
        return UriComponentsBuilder.fromUri(url)
            .path(name)
            .encode().build().toUri();
    }
}
