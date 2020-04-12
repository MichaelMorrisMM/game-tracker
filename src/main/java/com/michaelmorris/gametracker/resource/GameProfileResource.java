package com.michaelmorris.gametracker.resource;

import com.michaelmorris.gametracker.model.GameProfile;
import com.michaelmorris.gametracker.model.GameProfileFilter;
import com.michaelmorris.gametracker.model.Profile;
import com.michaelmorris.gametracker.service.GameProfileService;
import com.michaelmorris.gametracker.service.PlatformService;
import com.michaelmorris.gametracker.service.PriorityService;
import com.michaelmorris.gametracker.service.ProfileService;
import com.michaelmorris.gametracker.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game-profiles")
@RequiredArgsConstructor
@Slf4j
public class GameProfileResource {

    private final GameProfileService gameProfileService;
    private final ProfileService profileService;
    private final PriorityService priorityService;
    private final StatusService statusService;
    private final PlatformService platformService;

    @GetMapping
    public ResponseEntity<Page<GameProfile>> getGameProfiles(@RequestParam String profile, @RequestParam String page, @RequestParam String size,
                                                             @RequestParam(required = false) String filters, @RequestParam(required = false) String sorts) {

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

        GameProfileFilter filter = new GameProfileFilter(this.priorityService, this.statusService, this.platformService);
        List<Sort.Order> sortList = new ArrayList<>();
        try {
            if (filters != null) {
                for (String filterPair : filters.split(",")) {
                    String[] parsedFilterPair = filterPair.split(":");
                    filter.addFiltering(parsedFilterPair[0], parsedFilterPair[1]);
                }
            }
            if (sorts != null) {
                for (String sortPair : sorts.split(",")) {
                    String[] parsedSortPair = sortPair.split(":");
                    sortList.add(new Sort.Order(Sort.Direction.fromString(parsedSortPair[1]), parsedSortPair[0]));
                }
            }
        } catch (Exception e) {
            log.error("Unable to parse filtering and/or sorting", e);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this.gameProfileService.getGameProfiles(profileLookup.get(), pageParsed, sizeParsed, filter, sortList));
    }

}
