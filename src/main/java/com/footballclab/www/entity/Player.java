package com.footballclab.www.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club club;

    @Column(name = "player_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "citizenship", referencedColumnName = "country_id")
    private Country citizenship;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

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

    public Country getSitizenship() {
        return citizenship;
    }

    public void setSitizenship(Country sitizenship) {
        this.citizenship = sitizenship;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
