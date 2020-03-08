package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.repository.GameProfileRepository;
import com.michaelmorris.gametracker.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final GameProfileRepository gameProfileRepository;

    public Profile createProfile(String name) {
        var profile = new Profile();
        profile.setName(name);
        return this.profileRepository.save(profile);
    }

    public boolean profileExistsWithName(String name) {
        return this.profileRepository.existsByName(name);
    }

    public Iterable<Profile> getAllProfiles() {
        return this.profileRepository.findAll();
    }

    public boolean deleteProfile(String name) {
        Optional<Profile> lookup = this.profileRepository.findByName(name);
        if (lookup.isEmpty()) {
            return false;
        }
        Profile profile = lookup.get();
        this.gameProfileRepository.deleteAllByProfile(profile);
        this.profileRepository.delete(profile);
        return true;
    }

}
