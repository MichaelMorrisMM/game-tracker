package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.GamePlatform;
import com.michaelmorris.gametracker.repository.GamePlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamePlatformService {

    private final GamePlatformRepository gamePlatformRepository;

    public List<GamePlatform> getGamePlatformsForGame(Game game) {
        return this.gamePlatformRepository.findByGame(game);
    }

}
