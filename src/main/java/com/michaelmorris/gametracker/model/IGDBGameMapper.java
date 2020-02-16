package com.michaelmorris.gametracker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.Map;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("IGDB")
public class IGDBGameMapper extends GameMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_URL = "url";
    private static final String FIELD_TITLE = "name";

    public static final String[] QUERY_FIELDS = {FIELD_ID, FIELD_URL, FIELD_TITLE};

    @Override
    public void fromRecord(Map<String, Object> record) {
        this.setRef(Long.valueOf((Integer) record.get(FIELD_ID)));
        this.setUrl((String) record.get(FIELD_URL));
    }

    @Override
    public Game createGame(Map<String, Object> record) {
        Game game = new Game();
        game.setTitle((String) record.get(FIELD_TITLE));
        game.setMappers(Collections.singletonList(this));
        this.setGame(game);
        return game;
    }

}
