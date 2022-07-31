package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe;

import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.ports.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetMusicByTitleClient implements Client<String, AnimeMusic> {

    @Value("${themes.moe.client.music-by-title.url}")
    private final URI url;

    private final RestTemplate restTemplate;

    private final GetMusicByIdClient getMusicByIdClient;

    @Override
    public AnimeMusic executeCall(String title) {
        ResponseEntity<List<Integer>> response = restTemplate
            .exchange(toRequestEntity(title), new ParameterizedTypeReference<>() {
            });

        if (response.getStatusCode().is2xxSuccessful()) {
            final Integer id = response.getBody().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("Anime not found: " + title));
            return getMusicByIdClient.executeCall(id);
        }

        throw new IllegalStateException("Received get anime music by title response with status " +
            response.getStatusCode() + " and body " + response.getBody());
    }

    private RequestEntity<Void> toRequestEntity(final String title) {
        return RequestEntity.get(buildUriWithPathVariable(title)).build();
    }

    private URI buildUriWithPathVariable(final String title) {
        return UriComponentsBuilder.fromUri(url)
            .path(title)
            .encode().build().toUri();
    }
}
