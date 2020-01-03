package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.GameProfile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameProfileRepository extends PagingAndSortingRepository<GameProfile, Long> {

}
