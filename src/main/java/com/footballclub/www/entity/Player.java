package com.footballclub.www.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "player_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "citizenship", referencedColumnName = "country_id")
    private Country citizenship;



    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Club club, Position position, Country citizenship) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.citizenship = citizenship;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Country citizenship) {
        this.citizenship = citizenship;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
