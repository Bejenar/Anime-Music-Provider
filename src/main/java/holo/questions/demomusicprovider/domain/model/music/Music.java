package holo.questions.demomusicprovider.domain.model.music;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Music {

    private String title;
    private String url;
    private String type;
    private String notes;
}
