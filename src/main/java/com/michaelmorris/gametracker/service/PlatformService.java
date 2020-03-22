package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Platform;
import com.michaelmorris.gametracker.model.PlatformMapper;
import com.michaelmorris.gametracker.repository.PlatformMapperRepository;
import com.michaelmorris.gametracker.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;
    private final PlatformMapperRepository platformMapperRepository;

    public Optional<Platform> getPlatform(Long id) {
        return this.platformRepository.findById(id);
    }

    public Optional<Platform> getPlatform(String name) {
        return this.platformRepository.findByName(name);
    }

    public Platform createPlatform(Platform platform) {
        List<PlatformMapper> mappers = platform.getMappers();
        Platform savedPlatform = this.platformRepository.save(platform);
        mappers.forEach(mapper -> mapper.setPlatform(savedPlatform));
        List<PlatformMapper> savedMappers = new ArrayList<>();
        this.platformMapperRepository.saveAll(mappers).forEach(savedMappers::add);
        savedPlatform.setMappers(savedMappers);
        return savedPlatform;
    }

    public Platform updatePlatform(Platform platform) {
        Optional<Platform> lookup = this.getPlatform(platform.getId());
        if (lookup.isEmpty()) {
            return null;
        }
        Platform originalPlatform = lookup.get();
        originalPlatform.setName(platform.getName());
        return this.platformRepository.save(originalPlatform);
    }

}
