package com.michaelmorris.gametracker.resource;

import com.michaelmorris.gametracker.model.Platform;
import com.michaelmorris.gametracker.service.DatabaseWrapperService;
import com.michaelmorris.gametracker.service.PlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
@Slf4j
public class PlatformResource {

    private final PlatformService platformService;
    private final DatabaseWrapperService databaseWrapperService;

    @GetMapping("/{id}")
    public ResponseEntity<Platform> getPlatformById(@PathVariable String id) {
        return this.platformService.getPlatform(Long.parseLong(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Platform> getPlatformByName(@RequestParam String name) {
        return this.platformService.getPlatform(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Platform>> searchPlatformsByName(@RequestParam String name, @RequestParam(required = false) String limit) {
        try {
            Integer limitParsed = limit != null ? Integer.parseInt(limit) : null;
            return ResponseEntity.ok(this.databaseWrapperService.searchPlatformsByName(name, limitParsed));
        } catch (Exception e) {
            log.error("Error when searching by name " + name, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Platform> addPlatform(@RequestBody Platform platform) {
        if (this.platformService.getPlatform(platform.getName()).isPresent()) {
            log.error("Platform " + platform.getName() + " already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(this.platformService.createPlatform(platform));
    }

    @PutMapping
    public ResponseEntity<Platform> updatePlatform(@RequestBody Platform platform) {
        Platform updatedPlatform = this.platformService.updatePlatform(platform);
        if (updatedPlatform == null) {
            log.error("Cannot find platform with id " + platform.getId() + " to update");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPlatform);
    }

}
