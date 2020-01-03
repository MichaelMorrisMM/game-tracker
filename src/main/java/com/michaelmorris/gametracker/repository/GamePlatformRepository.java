package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.GamePlatform;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GamePlatformRepository extends PagingAndSortingRepository<GamePlatform, Long> {

}
