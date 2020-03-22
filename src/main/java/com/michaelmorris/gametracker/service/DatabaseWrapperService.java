package com.michaelmorris.gametracker.service;

import com.michaelmorris.gametracker.model.Game;
import com.michaelmorris.gametracker.model.Platform;

import java.util.List;

public interface DatabaseWrapperService {

    List<Game> searchByTitle(String title, Integer limit) throws Exception;

    List<Platform> searchPlatformsByName(String name, Integer limit) throws Exception;

}
