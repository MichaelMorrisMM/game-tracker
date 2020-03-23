package com.michaelmorris.gametracker.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Slf4j
public class JSONUtils {

    private static <T> T getValueOrDefault(String methodName, JSONObject object, T defaultValue, String... keys) {
        try {
            JSONObject currentObject = object;
            for (int i = 0; i < keys.length; i++) {
                if (currentObject.has(keys[i]) && !currentObject.isNull(keys[i])) {
                    if (i == keys.length - 1) {
                        return (T) currentObject.getClass().getDeclaredMethod(methodName, String.class).invoke(currentObject, keys[i]);
                    } else {
                        currentObject = currentObject.getJSONObject(keys[i]);
                    }
                } else {
                    return defaultValue;
                }
            }
        } catch (Exception e) {
            log.warn("Error when reading value from JSON object " + object.toString(), e);
        }
        return defaultValue;
    }

    public static String getStringOrDefault(JSONObject object, String defaultValue, String... keys) {
        return getValueOrDefault("getString", object, defaultValue, keys);
    }

    public static Long getLongOrDefault(JSONObject object, Long defaultValue, String... keys) {
        return getValueOrDefault("getLong", object, defaultValue, keys);
    }

}
