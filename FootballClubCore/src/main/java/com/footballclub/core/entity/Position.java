package com.footballclub.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_name")
    private String name;

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    @ToString.Exclude
    private List<Player> players;

    public Position(long id) {
        this.id = id;
    }

    public Position(String name) {
        this.name = name;
    }

    public Position(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
