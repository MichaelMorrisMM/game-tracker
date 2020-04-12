package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Priority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PriorityRepository extends CrudRepository<Priority, Long> {

    Optional<Priority> findByName(String name);

}
