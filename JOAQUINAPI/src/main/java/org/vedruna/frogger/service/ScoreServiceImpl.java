package org.vedruna.frogger.service;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.vedruna.frogger.persistance.model.Score;
import org.vedruna.frogger.persistance.model.User;
import org.vedruna.frogger.persistance.repository.ScoreRepository;
import org.vedruna.frogger.security.auth.dto.ScoreDTO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ScoreServiceImpl implements ScoreServiceI {

    @Autowired
    private ScoreRepository scoreRepository;

    private ScoreDTO scoreDTO;

    @Override
    public Page<ScoreDTO> getAllScores(Pageable pageable) {
        return scoreRepository.findAll(pageable).map(ScoreDTO::new);
    }

    @Override
    public Page<ScoreDTO> getAllScores(java.awt.print.Pageable pageable) {
        return null;
    }

    @Override
    public ScoreDTO getScoreByUser(User user) {

        return scoreDTO;
    }

    @Override
    public ScoreDTO insertScore(User user, ScoreDTO scoreDTO) {
        Score score = new Score(user, scoreDTO.getTime());
        score = scoreRepository.save(score);
        return new ScoreDTO(score);
    }

    @Override
    public void updateScore(User user, ScoreDTO scoreDTO) throws ValidationException {
        Score score = (Score) scoreRepository.findByUser(user)
                .orElseThrow(() -> new ValidationException("Puntuación no encontrada"));

        if (scoreDTO.getTime() > score.getTime()) {
            throw new ValidationException("El nuevo tiempo no puede ser mayor que el anterior.");
        }

        score.setTime(scoreDTO.getTime());
        scoreRepository.save(score);
    }
}
