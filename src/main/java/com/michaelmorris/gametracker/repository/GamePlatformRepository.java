package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.GamePlatform;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GamePlatformRepository extends PagingAndSortingRepository<GamePlatform, Long> {

    List<GamePlatform> findByGame(Game game);

}
