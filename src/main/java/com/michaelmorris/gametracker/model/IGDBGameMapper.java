package com.michaelmorris.gametracker.model;

import com.michaelmorris.gametracker.util.JSONUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("IGDB")
public class IGDBGameMapper extends GameMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_URL = "url";
    private static final String FIELD_TITLE = "name";
    private static final String FIELD_COVER_IMAGE_URL = "cover.url";

    public static final String[] QUERY_FIELDS = {FIELD_ID, FIELD_URL, FIELD_TITLE, FIELD_COVER_IMAGE_URL};

    public IGDBGameMapper(JSONObject record) {
        super(record);
    }

    @Override
    protected void fromRecord() {
        this.setRef(JSONUtils.getLongOrDefault(this.getDatabaseRecord(), null, FIELD_ID));
        this.setUrl(JSONUtils.getStringOrDefault(this.getDatabaseRecord(), null, FIELD_URL));
        this.setCoverImageUrl(JSONUtils.getStringOrDefault(this.getDatabaseRecord(), null, FIELD_COVER_IMAGE_URL.split("\\.")));
    }

    @Override
    public Game createGame() {
        Game game = new Game();
        game.setTitle(JSONUtils.getStringOrDefault(this.getDatabaseRecord(), null, FIELD_TITLE));
        game.setMappers(Collections.singletonList(this));
        this.setGame(game);
        return game;
    }

}
