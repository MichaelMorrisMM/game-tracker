package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile createProfile(String name) {
        var profile = new Profile();
        profile.setName(name);
        return this.profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles() {
        return StreamSupport.stream(this.profileRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

}
