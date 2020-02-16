package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.GameMapper;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameMapperRepository extends PagingAndSortingRepository<GameMapper, Long> {
}
