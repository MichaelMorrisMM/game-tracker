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
public class IGDBPlatformMapper extends PlatformMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_URL = "url";
    private static final String FIELD_TITLE = "name";

    public static final String[] QUERY_FIELDS = {FIELD_ID, FIELD_URL, FIELD_TITLE};

    public IGDBPlatformMapper(Map<String, Object> record) {
        super(record);
    }

    @Override
    protected void fromRecord() {
        this.setRef(Long.valueOf((Integer) this.getDatabaseRecord().get(FIELD_ID)));
        this.setUrl((String) this.getDatabaseRecord().get(FIELD_URL));
    }

    @Override
    public Platform createPlatform() {
        Platform platform = new Platform();
        platform.setName((String) this.getDatabaseRecord().get(FIELD_TITLE));
        platform.setMappers(Collections.singletonList(this));
        this.setPlatform(platform);
        return platform;
    }

}
