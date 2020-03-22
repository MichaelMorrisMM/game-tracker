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
public class IGDBPlatformMapper extends PlatformMapper {

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
    public Platform createPlatform(Map<String, Object> record) {
        Platform platform = new Platform();
        platform.setName((String) record.get(FIELD_TITLE));
        platform.setMappers(Collections.singletonList(this));
        this.setPlatform(platform);
        return platform;
    }


}
