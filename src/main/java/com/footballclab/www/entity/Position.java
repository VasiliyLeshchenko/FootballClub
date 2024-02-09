package com.footballclab.www.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @Column(name = "position_id")
    private long id;

    @Column(name = "position_name")
    private String name;

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private List<Player> players;

    public Position() {}

    public Position(String name) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
