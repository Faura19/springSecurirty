package org.vedruna.frogger.security.auth.controller;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import org.vedruna.frogger.persistance.model.Score;
import org.vedruna.frogger.persistance.model.User;
import org.vedruna.frogger.security.auth.dto.ScoreDTO;
import org.vedruna.frogger.service.ScoreServiceI;


@RestController
@RequestMapping("/api/v1/score")
@CrossOrigin
public class ScoreController {

    @Autowired
    private ScoreServiceI scoreService;

    // 8) GET /score -> Obtener todas las puntuaciones paginadas
    @GetMapping
    public ResponseEntity<Page<ScoreDTO>> getAllScores(Pageable pageable) {
        Page<ScoreDTO> scores = scoreService.getAllScores((java.awt.print.Pageable) pageable);
        return ResponseEntity.ok(scores);
    }

    // 9) GET /score/me -> Obtener la puntuación del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<ScoreDTO> getMyScore(@AuthenticationPrincipal User user) {
        ScoreDTO score = scoreService.getScoreByUser(user);
        if (score != null) {
            return ResponseEntity.ok(score);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 10) POST /score -> Insertar puntuación de un usuario
    @PostMapping
    public ResponseEntity<ScoreDTO> insertScore(@AuthenticationPrincipal User user, @RequestBody ScoreDTO scoreDTO) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ScoreDTO newScore = scoreService.insertScore(user, scoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newScore);
    }

    // 11) PUT /score -> Editar puntuación, validando el tiempo
    @PutMapping
    public ResponseEntity<String> updateScore(@AuthenticationPrincipal User user, @RequestBody ScoreDTO scoreDTO) {
        try {
            scoreService.updateScore(user, scoreDTO);
            return ResponseEntity.ok("Puntuación actualizada correctamente.");
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nuevo tiempo no puede ser mayor que el anterior.");
        }
    }
}
