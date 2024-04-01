package com.footballclub.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Club")
public class Club {
    @Id
    @Column(name = "club_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "club")
    @JsonIgnore
    private List<PlayerStatistics> playersStatistics;

    public Club(String name) {
        this.name = name;
    }
}
