package org.vedruna.frogger.service;

import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vedruna.frogger.persistance.model.Score;
import org.vedruna.frogger.persistance.model.User;
import org.vedruna.frogger.security.auth.dto.ScoreDTO;

import java.awt.print.Pageable;


public interface ScoreServiceI {

    Page<ScoreDTO> getAllScores(org.springframework.data.domain.Pageable pageable);

    Page<ScoreDTO> getAllScores(Pageable pageable);
    ScoreDTO getScoreByUser(User user);
    ScoreDTO insertScore(User user, ScoreDTO scoreDTO);
    void updateScore(User user, ScoreDTO scoreDTO) throws ValidationException;

}
