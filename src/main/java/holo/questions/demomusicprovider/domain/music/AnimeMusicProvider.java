package holo.questions.demomusicprovider.domain.music;

import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.model.music.Music;
import holo.questions.demomusicprovider.domain.ports.Client;
import holo.questions.demomusicprovider.domain.ports.NoArgClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimeMusicProvider {

    private final Client<String, AnimeMusic> musicByTitleClient;

    private final Client<Integer, AnimeMusic> musicByIdClient;

    private final NoArgClient<AnimeMusic> randomMusicClient;

    private final Client<String, List<AnimeMusic>> musicByArtistClient;

    public AnimeMusic getMusicById(final Integer id) {
        return musicByIdClient.executeCall(id);
    }

    public AnimeMusic getMusicByTitle(final String title) {
        return musicByTitleClient.executeCall(title);
    }

    public AnimeMusic getRandomMusic() {
        return randomMusicClient.executeCall();
    }

    public List<AnimeMusic> getMusicByArtist(final String name) {
        return musicByArtistClient.executeCall(name);
    }
}
