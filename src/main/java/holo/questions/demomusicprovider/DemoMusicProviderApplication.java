package holo.questions.demomusicprovider;

import holo.questions.demomusicprovider.domain.model.music.AnimeMusic;
import holo.questions.demomusicprovider.domain.model.music.Music;
import holo.questions.demomusicprovider.domain.music.AnimeMusicProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoMusicProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMusicProviderApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final AnimeMusicProvider provider) {
        return args -> {
            AnimeMusic randomMusic = provider.getRandomMusic();
            List<AnimeMusic> aimer = provider.getMusicByArtist("aimer");
            AnimeMusic made_in = provider.getMusicByTitle("Made in");
            AnimeMusic musicById = provider.getMusicById(37521);
            System.out.println();
        };
    }

}
