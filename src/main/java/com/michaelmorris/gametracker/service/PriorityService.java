package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Priority;
import com.michaelmorris.gametracker.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public Optional<Priority> getPriority(String name) {
        return this.priorityRepository.findByName(name);
    }

    public List<Priority> getAllPriorities() {
        List<Priority> priorities = new ArrayList<>();
        this.priorityRepository.findAll().forEach(priorities::add);
        return priorities;
    }

}
