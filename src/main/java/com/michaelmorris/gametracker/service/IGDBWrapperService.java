package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.util.IGDBQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IGDBWrapperService implements DatabaseWrapperService {

    private static final String IGDB_BASE_URL = "https://api-v3.igdb.com";
    private static final String HEADER_KEY = "user-key";

    @Value("${api_key}")
    private String API_KEY;

    @Override
    public List<Game> searchByTitle(String title, Integer limit) {
        IGDBQuery query = new IGDBQuery()
                .addSearch(title);
        if (limit != null) {
            query.addLimit(limit);
        }
        String body = query.build();

        // TODO

        return null;
    }

}
