package org.vedruna.frogger.persistance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Timer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "record_scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer record_id;

    @Column(name="record_scorescol",nullable = false)
    private Time record_scorescol; // Guardamos el tiempo como String (Formato "HH:mm:ss")

    @ManyToOne
    @JoinColumn(name = "users_user_id", nullable = false)
    private User user;

    public Score(User user, Double time) {
    }


    public Integer getTime() {
        return record_id;
    }

    public void setTime(Double time) {
    }


    public Integer getId() {
        return record_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
