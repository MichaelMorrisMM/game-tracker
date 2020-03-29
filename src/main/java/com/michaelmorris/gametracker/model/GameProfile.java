package com.michaelmorris.gametracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;

    @Column
    @Lob
    private String notes;

    @Column
    private LocalDate lastPlayedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Platform> platforms;

}
