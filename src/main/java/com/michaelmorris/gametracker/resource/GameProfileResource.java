package com.michaelmorris.gametracker.resource;

import com.michaelmorris.gametracker.model.GameProfile;
import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.service.GameProfileService;
import com.michaelmorris.gametracker.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/game-profiles")
@RequiredArgsConstructor
@Slf4j
public class GameProfileResource {

    private final GameProfileService gameProfileService;
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<GameProfile>> getGameProfiles(@RequestParam String profile, @RequestParam String page, @RequestParam String size) {
        Optional<Profile> profileLookup = this.profileService.getProfileByName(profile);
        if (profileLookup.isEmpty()) {
            log.error("Unable to find profile with name " + profile);
            return ResponseEntity.badRequest().build();
        }

        int pageParsed;
        int sizeParsed;
        try {
            pageParsed = Integer.parseInt(page);
            sizeParsed = Integer.parseInt(size);
        } catch (NumberFormatException e) {
            log.error("Unable to parse page and size", e);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this.gameProfileService.getGameProfiles(profileLookup.get(), pageParsed, sizeParsed));
    }

}
