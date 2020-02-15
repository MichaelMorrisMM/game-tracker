package com.michaelmorris.gametracker.resource;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.service.DatabaseWrapperService;
import com.michaelmorris.gametracker.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Slf4j
public class GameResource {

    private final GameService gameService;
    private final DatabaseWrapperService databaseWrapperService;

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return this.gameService.getGame(Long.parseLong(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Game> getGameByTitle(@RequestParam String title) {
        return this.gameService.getGame(title)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchGamesByTitle(@RequestParam String title, @RequestParam(required = false) String limit) {
        try {
            Integer limitParsed = limit != null ? Integer.parseInt(limit) : null;
            return ResponseEntity.ok(this.databaseWrapperService.searchByTitle(title, limitParsed));
        } catch (Exception e) {
            log.error("Error when searching by title " + title, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
