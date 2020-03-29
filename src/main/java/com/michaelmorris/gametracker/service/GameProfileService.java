package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.GameProfile;
import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.repository.GameProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameProfileService {

    private final GameProfileRepository gameProfileRepository;

    public Page<GameProfile> getGameProfiles(Profile profile, int page, int size) {
        Sort sort = Sort.by("game.title").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return this.gameProfileRepository.findByProfile(profile, pageable);
    }

}
