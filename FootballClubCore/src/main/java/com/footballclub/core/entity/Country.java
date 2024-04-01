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
    private List<Club> clubs;

    @OneToMany(mappedBy = "citizenship")
    @JsonIgnore
    private List<Player> players;

    public Country(long id) {
        this.id = id;
    }

    public Country(String name) {
        this.name = name;
    }

}
