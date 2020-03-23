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
public class IGDBPlatformMapper extends PlatformMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_URL = "url";
    private static final String FIELD_TITLE = "name";
    private static final String FIELD_LOGO_IMAGE_URL = "platform_logo.url";

    public static final String[] QUERY_FIELDS = {FIELD_ID, FIELD_URL, FIELD_TITLE, FIELD_LOGO_IMAGE_URL};

    public IGDBPlatformMapper(JSONObject record) {
        super(record);
    }

    @Override
    protected void fromRecord() {
        this.setRef(JSONUtils.getLongOrDefault(this.getDatabaseRecord(), null, FIELD_ID));
        this.setUrl(JSONUtils.getStringOrDefault(this.getDatabaseRecord(), null, FIELD_URL));
        this.setLogoImageUrl(JSONUtils.getStringOrDefault(this.getDatabaseRecord(), null, FIELD_LOGO_IMAGE_URL.split("\\.")));
    }

    @Override
    public Platform createPlatform() {
        Platform platform = new Platform();
        platform.setName(JSONUtils.getStringOrDefault(this.getDatabaseRecord(), null, FIELD_TITLE));
        platform.setMappers(Collections.singletonList(this));
        this.setPlatform(platform);
        return platform;
    }

}
