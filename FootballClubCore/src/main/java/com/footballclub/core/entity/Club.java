package com.footballclub.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
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
    @ToString.Exclude
    private List<Player> players;

    @OneToMany(mappedBy = "homeClub")
    @JsonIgnore
    @ToString.Exclude
    private List<Game> homeGames;

    @OneToMany(mappedBy = "awayClub")
    @JsonIgnore
    @ToString.Exclude
    private List<Game> awayGames;

    @OneToMany(mappedBy = "club")
    @JsonIgnore
    @ToString.Exclude
    private List<PlayerStatistics> playersStatistics;

    public Club(Long id) {
        this.id = id;
    }

    public Club(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

}
