package com.michaelmorris.gametracker.resource;

import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
@Slf4j
public class ProfileResource {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Iterable<Profile>> getAllProfiles() {
        return ResponseEntity.ok(this.profileService.getAllProfiles());
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestParam String name) {
        if (this.profileService.profileExistsWithName(name)) {
            log.error("Profile with name " + name + " already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(this.profileService.createProfile(name));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteProfile(@RequestParam String name) {
        if (!this.profileService.profileExistsWithName(name)) {
            log.error("Profile with name " + name + " does not exist to delete");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.profileService.deleteProfile(name));
    }

}
