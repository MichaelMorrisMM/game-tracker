package com.michaelmorris.gametracker.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IGDBQuery {

    private static final String NEW_LINE = System.lineSeparator();

    private StringBuilder builder = new StringBuilder();

    public IGDBQuery addSearch(String text) {
        this.builder.append("search \"");
        this.builder.append(text);
        this.builder.append("\";");
        this.builder.append(NEW_LINE);
        return this;
    }

    public IGDBQuery addAllFields() {
        this.builder.append("fields *;");
        this.builder.append(NEW_LINE);
        return this;
    }

    public IGDBQuery addFields(String... fields) {
        this.builder.append("fields ");
        for (String field : fields) {
            this.builder.append(field);
            this.builder.append(", ");
        }
        this.builder.setLength(this.builder.length() - 2);
        this.builder.append(";");
        this.builder.append(NEW_LINE);
        return this;
    }

    public IGDBQuery addLimit(int limit) {
        this.builder.append("limit ");
        this.builder.append(limit);
        this.builder.append(";");
        this.builder.append(NEW_LINE);
        return this;
    }

    public String build() {
        return this.builder.toString();
    }

}
