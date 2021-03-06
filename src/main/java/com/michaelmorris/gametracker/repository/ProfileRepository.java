package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

    Optional<Profile> findByName(String name);

    boolean existsByName(String name);

    @Query("select distinct name from Profile")
    List<String> findAllNames();

}
