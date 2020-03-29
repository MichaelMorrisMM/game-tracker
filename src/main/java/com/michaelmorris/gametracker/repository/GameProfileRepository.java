package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.GameProfile;
import com.michaelmorris.gametracker.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameProfileRepository extends PagingAndSortingRepository<GameProfile, Long> {

    void deleteAllByProfile(Profile profile);

    Page<GameProfile> findByProfile(Profile profile, Pageable pageable);

}
