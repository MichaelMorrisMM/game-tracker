package com.michaelmorris.gametracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.IGDBGameMapper;
import com.michaelmorris.gametracker.model.IGDBPlatformMapper;
import com.michaelmorris.gametracker.model.Platform;
import com.michaelmorris.gametracker.repository.GameRepository;
import com.michaelmorris.gametracker.repository.PlatformRepository;
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

    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;

    private static final String IGDB_BASE_URL = "https://api-v3.igdb.com";
    private static final String HEADER_KEY = "user-key";

    private static final String ENDPOINT_GAMES = "games";
    private static final String ENDPOINT_PLATFORMS = "platforms";

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

    private List<Map<String, Object>> search(String searchString, String endpoint, Integer limit, String... fields) throws Exception {
        IGDBQuery query = new IGDBQuery()
                .addSearch(searchString)
                .addFields(fields);
        if (limit != null) {
            query.addLimit(limit);
        }
        HttpResponse<String> response = sendPostRequest(endpoint, query.build());
        return objectMapper.readValue(response.body(), new TypeReference<>(){});
    }

    @Override
    public List<Game> searchByTitle(String title, Integer limit) throws Exception {
        List<Map<String, Object>> array = this.search(title, ENDPOINT_GAMES, limit, IGDBGameMapper.QUERY_FIELDS);
        List<Game> games = new ArrayList<>();
        array.forEach(gameResult -> {
            Game tempGame = new IGDBGameMapper(gameResult).createGame();
            this.gameRepository.findByTitle(tempGame.getTitle()).ifPresentOrElse(games::add, () -> games.add(tempGame));
        });
        return games;
    }

    @Override
    public List<Platform> searchPlatformsByName(String name, Integer limit) throws Exception {
        List<Map<String, Object>> array = this.search(name, ENDPOINT_PLATFORMS, limit, IGDBPlatformMapper.QUERY_FIELDS);
        List<Platform> platforms = new ArrayList<>();
        array.forEach(platformResult -> {
            Platform tempPlatform = new IGDBPlatformMapper(platformResult).createPlatform();
            this.platformRepository.findByName(tempPlatform.getName()).ifPresentOrElse(platforms::add, () -> platforms.add(tempPlatform));
        });
        return platforms;
    }


}
