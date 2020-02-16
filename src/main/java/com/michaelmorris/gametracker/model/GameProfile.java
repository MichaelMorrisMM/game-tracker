package com.michaelmorris.gametracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class GameProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;

    @Column
    private LocalDate lastPlayedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Platform> platforms;

}
