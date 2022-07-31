package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe;

import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.ports.Client;
import holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto.SearchAnimeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class GetMusicByIdClient implements Client<Integer, AnimeMusic> {

    @Value("${themes.moe.client.music-by-id.url}")
    private final URI url;

    private final RestTemplate restTemplate;

    private final Converter<SearchAnimeResponseDto, AnimeMusic> converter;

    @Override
    public AnimeMusic executeCall(Integer id) {
        ResponseEntity<SearchAnimeResponseDto> response = restTemplate
            .exchange(toRequestEntity(id), SearchAnimeResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return converter.convert(response.getBody());
        }

        throw new IllegalStateException("Received get anime music by id response with status " +
            response.getStatusCode() + " and body " + response.getBody());
    }

    private RequestEntity<Void> toRequestEntity(final Integer id) {
        return RequestEntity.get(buildUriWithPathVariable(id)).build();
    }

    private URI buildUriWithPathVariable(final Integer id) {
        return UriComponentsBuilder.fromUri(url)
            .path(id.toString())
            .encode().build().toUri();
    }
}
