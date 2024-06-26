package com.footballclub.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Класс игрока со свойствами <b>id</b>, <b>name</b>, <b>position</b>, <b>club</b>, <b>citizenship</b>, <b>playerStatistics</b>
 * @author Leshchenko Vasiliy
 * @version 0.0.1-SNAPHOT
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "Player")
public class Player {
    /** Поле id */
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Поле имя игрока */
    @Column(name = "player_name")
    private String name;

    /** Поле позиция игрока */
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

    /** Поле клуб игрока */
    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club club;

    /** Поле гражданство игрока */
    @ManyToOne
    @JoinColumn(name = "citizenship", referencedColumnName = "country_id")
    private Country citizenship;

    /** Поле статистика игрока */
    @OneToMany(mappedBy = "player")
    @JsonIgnore
    @ToString.Exclude
    private List<PlayerStatistics> playerStatistics;

    /**
     * Конструктор - создания объекта с определенным значением
     * @param id - id игрока
     */
    public Player(long id) {
        this.id = id;
    }
    /**
     * Конструктор - создания объекта с определенным значением
     * @param name - имя игрока
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Конструктор - создания объекта с определенным значением
     * @param name - имя игрока
     * @param club - клуб игрока
     * @param position - позиция игрока
     * @param citizenship - гражданство игрока
     */
    public Player(String name, Club club, Position position, Country citizenship) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.citizenship = citizenship;
    }

    /**
     * Конструктор - создания объекта с определенным значением
     * @param id - id игрока
     * @param name - имя игрока
     * @param club - клуб игрока
     * @param position - позиция игрока
     * @param citizenship - гражданство игрока
     */
    public Player(Long id, String name, Position position, Club club, Country citizenship) {
        this(name, club, position, citizenship);
        this.id = id;
    }
}
