package com.footballclub.www.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Club")
public class Club {

    @Id
    @Column(name = "club_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "club_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    @OneToMany(mappedBy = "club")
    @JsonIgnore
    private List<Player> players;

    @OneToMany(mappedBy = "homeClub")
    @JsonIgnore
    private List<Game> homeGames;

    @OneToMany(mappedBy = "awayClub")
    @JsonIgnore
    private List<Game> awayGames;

    public Club() {}

    public Club(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Game> getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(List<Game> homeGames) {
        this.homeGames = homeGames;
    }

    public List<Game> getAwayGames() {
        return awayGames;
    }

    public void setAwayGames(List<Game> awayGames) {
        this.awayGames = awayGames;
    }
}
