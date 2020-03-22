package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Platform;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PlatformRepository extends PagingAndSortingRepository<Platform, Long> {

    Optional<Platform> findByName(String name);

}
