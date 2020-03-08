package com.michaelmorris.gametracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.GameMapper;
import com.michaelmorris.gametracker.model.IGDBGameMapper;
import com.michaelmorris.gametracker.util.IGDBQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IGDBWrapperService implements DatabaseWrapperService {

    private static final String IGDB_BASE_URL = "https://api-v3.igdb.com";
    private static final String HEADER_KEY = "user-key";

    private static final String ENDPOINT_GAMES = "games";

    private static HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api_key}")
    private String API_KEY;

    private HttpResponse<String> sendPostRequest(String endpoint, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(IGDB_BASE_URL + "/" + endpoint))
                .header(HEADER_KEY, API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public List<Game> searchByTitle(String title, Integer limit) throws Exception {
        IGDBQuery query = new IGDBQuery()
                .addSearch(title)
                .addFields(IGDBGameMapper.QUERY_FIELDS);
        if (limit != null) {
            query.addLimit(limit);
        }

        HttpResponse<String> response = sendPostRequest(ENDPOINT_GAMES, query.build());
        List<Map<String, Object>> array = objectMapper.readValue(response.body(), new TypeReference<>(){});

        List<Game> games = new ArrayList<>();
        array.forEach(game -> {
            GameMapper mapper = new IGDBGameMapper();
            mapper.fromRecord(game);
            games.add(mapper.createGame(game));
        });

        return games;
    }

}
