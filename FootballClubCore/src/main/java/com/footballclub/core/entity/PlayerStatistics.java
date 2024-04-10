package com.footballclub.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;

@Data
@NoArgsConstructor
@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club club;

    @Column(name = "goals")
    private Integer goals;

    public PlayerStatistics(Long id) {
        this.id = id;
    }

    public PlayerStatistics(Long id, Player player, Club club, Integer goals) {
        this.id = id;
        this.player = player;
        this.club = club;
        this.goals = goals;
    }
}
