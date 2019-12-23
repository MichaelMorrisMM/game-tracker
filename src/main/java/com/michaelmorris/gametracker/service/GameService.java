package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Optional<Game> getGame(Long id) {
        return this.gameRepository.findById(id);
    }

    public Optional<Game> getGame(String title) {
        return this.gameRepository.findByTitle(title);
    }

}
