package com.footballclub.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_club_id", referencedColumnName = "club_id")
    private Club homeClub;

    @ManyToOne
    @JoinColumn(name = "away_club_id", referencedColumnName = "club_id")
    private Club awayClub;

    @Column(name = "home_club_score")
    private Integer homeClubScore;

    @Column(name = "away_club_score")
    private Integer awayClubScore;

    @Column(name = "game_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Game(long id) {
        this.id = id;
    }

    public Game(Club homeClub, Club awayClub, int homeClubScore, int awayClubScore, Date date) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeClubScore = homeClubScore;
        this.awayClubScore = awayClubScore;
        this.date = date;
    }

    public Game(Long id, Club homeClub, Club awayClub, Integer homeClubScore, Integer awayClubScore, Date date) {
        this(homeClub, awayClub, homeClubScore, awayClubScore, date);
        this.id = id;
    }
}
