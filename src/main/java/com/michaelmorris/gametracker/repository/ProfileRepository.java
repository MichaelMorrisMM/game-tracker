package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

}
