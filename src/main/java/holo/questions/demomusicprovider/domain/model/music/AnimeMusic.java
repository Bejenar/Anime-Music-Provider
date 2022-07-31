package holo.questions.demomusicprovider.domain.model.music;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimeMusic {

    private Integer malID;
    private String title;
    private String season;
    private List<Music> music;
}
