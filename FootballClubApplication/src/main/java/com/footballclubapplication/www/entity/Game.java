package com.footballclubapplication.www.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Game")
public class Game {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "home_club_id", referencedColumnName = "club_id")
    private Club homeClub;

    @ManyToOne
    @JoinColumn(name = "away_club_id", referencedColumnName = "club_id")
    private Club awayClub;

    @Column(name = "home_club_score")
    private int homeClubScore;

    @Column(name = "away_club_score")
    private int awayClubScore;

    @Column(name = "game_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Game() {}

    public Game(Club homeClub, Club awayClub, int homeClubScore, int awayClubScore, Date date) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.homeClubScore = homeClubScore;
        this.awayClubScore = awayClubScore;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Club getHomeClub() {
        return homeClub;
    }

    public void setHomeClub(Club homeClub) {
        this.homeClub = homeClub;
    }

    public Club getAwayClub() {
        return awayClub;
    }

    public void setAwayClub(Club awayClub) {
        this.awayClub = awayClub;
    }

    public int getHomeClubScore() {
        return homeClubScore;
    }

    public void setHomeClubScore(int homeClubScore) {
        this.homeClubScore = homeClubScore;
    }

    public int getAwayClubScore() {
        return awayClubScore;
    }

    public void setAwayClubScore(int awayClubScore) {
        this.awayClubScore = awayClubScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
