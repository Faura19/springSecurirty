package org.vedruna.frogger.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vedruna.frogger.persistance.model.Score;
import org.vedruna.frogger.persistance.model.User;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {

    Optional<Object> findByUser(User user);

}
