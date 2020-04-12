package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Optional<Status> findByName(String name);

}
