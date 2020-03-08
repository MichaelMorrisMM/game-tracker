package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Platform;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlatformRepository extends PagingAndSortingRepository<Platform, Long> {
}
