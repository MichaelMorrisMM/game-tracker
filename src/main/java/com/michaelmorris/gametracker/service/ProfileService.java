package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile createProfile(String name) {
        var profile = new Profile();
        profile.setName(name);
        return this.profileRepository.save(profile);
    }

    public Iterable<Profile> getAllProfiles() {
        return this.profileRepository.findAll();
    }

}
