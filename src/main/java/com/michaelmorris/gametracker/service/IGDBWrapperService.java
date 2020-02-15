package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.util.IGDBQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IGDBWrapperService implements DatabaseWrapperService {

    private static final String IGDB_BASE_URL = "https://api-v3.igdb.com";
    private static final String HEADER_KEY = "user-key";

    private static final String ENDPOINT_GAMES = "games";

    private static HttpClient client;

    @Value("${api_key}")
    private String API_KEY;

    private HttpResponse<String> sendPostRequest(String endpoint, String body) throws IOException, InterruptedException {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(IGDB_BASE_URL + "/" + endpoint))
                .header(HEADER_KEY, API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public List<Game> searchByTitle(String title, Integer limit) throws Exception {
        // TODO
        IGDBQuery query = new IGDBQuery()
                .addSearch(title);
        if (limit != null) {
            query.addLimit(limit);
        }
        HttpResponse<String> response = sendPostRequest(ENDPOINT_GAMES, query.build());

        return null;
    }

}
