package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Game;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

    Optional<Game> findByTitle(String title);

}
