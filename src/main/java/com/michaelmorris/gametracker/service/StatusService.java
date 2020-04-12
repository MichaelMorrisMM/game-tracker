package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Status;
import com.michaelmorris.gametracker.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public Optional<Status> getStatus(String name) {
        return this.statusRepository.findByName(name);
    }

    public List<Status> getAllStatuses() {
        List<Status> statuses = new ArrayList<>();
        this.statusRepository.findAll().forEach(statuses::add);
        return statuses;
    }

}
