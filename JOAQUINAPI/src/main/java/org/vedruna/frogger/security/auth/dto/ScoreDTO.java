package org.vedruna.frogger.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.vedruna.frogger.persistance.model.Score;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private Integer id;
    private Double time;

    public ScoreDTO(Score score) {
        this.id = score.getId();
        this.time = Double.valueOf(score.getTime());
    }

    public String ScoreDTO(Object o) {
        return "";
    }
}
