package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.GameProfile;
import com.michaelmorris.gametracker.model.GameProfileFilter;
import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.repository.GameProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameProfileService {

    private final GameProfileRepository gameProfileRepository;

    private static final String DEFAULT_SORT_FIELD = "game.title";

    public Page<GameProfile> getGameProfiles(Profile profile, int page, int size, GameProfileFilter filter, List<Sort.Order> sorting) {
        Sort sort;
        if (sorting.isEmpty()) {
            sort = Sort.by(Sort.Direction.ASC, DEFAULT_SORT_FIELD);
        } else {
            sort = Sort.by(sorting);
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        if (filter.isFilteringByPlatform()) {
            return this.gameProfileRepository.findByCustomFilterWithPlatform(profile, filter.getPriorities(), filter.getStatuses(), filter.getPlatform(), pageable);
        } else if (filter.isFilteringByPriority() || filter.isFilteringByStatus()) {
            return this.gameProfileRepository.findByCustomFilter(profile, filter.getPriorities(), filter.getStatuses(), pageable);
        } else {
            return this.gameProfileRepository.findByProfile(profile, pageable);
        }
    }

}
