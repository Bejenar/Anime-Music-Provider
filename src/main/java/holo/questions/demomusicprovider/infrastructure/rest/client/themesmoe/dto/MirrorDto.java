package holo.questions.demomusicprovider.infrastructure.rest.client.themesmoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MirrorDto {

    private String mirrorURL;
    private String priority;
    private String notes;
}
