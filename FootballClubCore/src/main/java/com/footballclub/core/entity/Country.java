package com.footballclub.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    @ToString.Exclude
    private List<Club> clubs;

    @OneToMany(mappedBy = "citizenship")
    @JsonIgnore
    @ToString.Exclude
    private List<Player> players;

    public Country(long id) {
        this.id = id;
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
