package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe;


import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.ports.NoArgClient;
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
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class GetRandomMusicClient implements NoArgClient<AnimeMusic> {

    private final static String SEED_PARAM_NAME = "a";

    @Value("${themes.moe.client.random-music.url}")
    private final URI url;

    private final RestTemplate restTemplate;

    private final Converter<SearchAnimeResponseDto, AnimeMusic> converter;

    private final Random random = new SecureRandom();

    @Override
    public AnimeMusic executeCall() {
        ResponseEntity<SearchAnimeResponseDto> response = restTemplate
            .exchange(toRequestEntity(), SearchAnimeResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return converter.convert(response.getBody());
        }

        throw new IllegalStateException("Received get random anime music response with status " +
            response.getStatusCode() + " and body " + response.getBody());
    }

    private RequestEntity<Void> toRequestEntity() {
        return RequestEntity.get(buildUriWithPathVariable()).build();
    }

    private URI buildUriWithPathVariable() {
        return UriComponentsBuilder.fromUri(url)
            .queryParam(SEED_PARAM_NAME, generateSeed())
            .encode().build().toUri();
    }

    private long generateSeed() {
        return Instant.now().toEpochMilli() + random.nextLong(1000);
    }
}
