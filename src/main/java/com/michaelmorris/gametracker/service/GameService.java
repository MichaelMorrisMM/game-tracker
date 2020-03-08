package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.GameMapper;
import com.michaelmorris.gametracker.repository.GameMapperRepository;
import com.michaelmorris.gametracker.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapperRepository gameMapperRepository;

    public Optional<Game> getGame(Long id) {
        return this.gameRepository.findById(id);
    }

    public Optional<Game> getGame(String title) {
        return this.gameRepository.findByTitle(title);
    }

    public Game createGame(Game game) {
        List<GameMapper> mappers = game.getMappers();
        Game savedGame = this.gameRepository.save(game);
        mappers.forEach(mapper -> mapper.setGame(savedGame));
        List<GameMapper> savedMappers = new ArrayList<>();
        this.gameMapperRepository.saveAll(mappers).forEach(savedMappers::add);
        savedGame.setMappers(savedMappers);
        return savedGame;
    }

    public Game updateGame(Game game) {
        Optional<Game> lookup = this.getGame(game.getId());
        if (lookup.isEmpty()) {
            return null;
        }
        Game originalGame = lookup.get();
        originalGame.setTitle(game.getTitle());
        return this.gameRepository.save(originalGame);
    }

}
