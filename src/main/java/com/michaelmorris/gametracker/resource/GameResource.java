package com.michaelmorris.gametracker.resource;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.GamePlatform;
import com.michaelmorris.gametracker.service.GamePlatformService;
import com.michaelmorris.gametracker.service.GameService;
import lombok.RequiredArgsConstructor;
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
public class GameResource {

    private final GameService gameService;
    private final GamePlatformService gamePlatformService;

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

    @GetMapping("/game-platforms")
    public ResponseEntity<List<GamePlatform>> getGamePlatforms(@RequestParam String gameId) {
        return this.gameService.getGame(Long.parseLong(gameId))
                .map((game) -> ResponseEntity.ok(this.gamePlatformService.getGamePlatformsForGame(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
