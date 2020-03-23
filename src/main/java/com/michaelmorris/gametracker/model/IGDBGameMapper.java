package com.michaelmorris.gametracker.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("IGDB")
public class IGDBGameMapper extends GameMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_URL = "url";
    private static final String FIELD_TITLE = "name";

    public static final String[] QUERY_FIELDS = {FIELD_ID, FIELD_URL, FIELD_TITLE};

    public IGDBGameMapper(Map<String, Object> record) {
        super(record);
    }

    @Override
    protected void fromRecord() {
        this.setRef(Long.valueOf((Integer) this.getDatabaseRecord().get(FIELD_ID)));
        this.setUrl((String) this.getDatabaseRecord().get(FIELD_URL));
    }

    @Override
    public Game createGame() {
        Game game = new Game();
        game.setTitle((String) this.getDatabaseRecord().get(FIELD_TITLE));
        game.setMappers(Collections.singletonList(this));
        this.setGame(game);
        return game;
    }

}
