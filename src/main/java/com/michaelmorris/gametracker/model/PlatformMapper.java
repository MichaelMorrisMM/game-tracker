package com.michaelmorris.gametracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@DiscriminatorColumn(name = "database", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = IGDBPlatformMapper.class, name = "IGDB")})
public abstract class PlatformMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long ref;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Platform platform;

    @Transient
    @JsonIgnore
    private Map<String, Object> databaseRecord;

    public PlatformMapper(Map<String, Object> record) {
        this.databaseRecord = record;
        this.fromRecord();
    }

    protected abstract void fromRecord();

    public abstract Platform createPlatform();

}
