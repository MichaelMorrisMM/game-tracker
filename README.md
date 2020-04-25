# Game Tracker
Tracks statistics on played games and records a to-play list of released and upcoming games.

## Setup

### API key
In order to leverage the video game database [IGDB](https://www.igdb.com/discover), obtain a free API key [here](https://www.igdb.com/api).

### Docker
Use the provided Dockerfile to build and run a Docker image of the application.
It is necessary to provide the API key obtained above as an environment variable in the container.
For example, the following can be run from the project root directory:

`docker build -t game-tracker:1.0 . && docker run -p 8080:8080 --env api_key=<INSERT API KEY HERE> --env SPRING_PROFILES_ACTIVE=prod --name game-tracker game-tracker:1.0`
