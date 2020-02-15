package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;

import java.util.List;

public interface DatabaseWrapperService {

    List<Game> searchByTitle(String title, Integer limit);

}
