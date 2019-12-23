INSERT INTO game (id, title) VALUES (1, 'Halo: Combat Evolved');

INSERT INTO system (id, name) VALUES (1, 'Xbox');

INSERT INTO developer (id, name) VALUES (1, 'Bungie');

INSERT INTO rating (id, name) VALUES (1, 'M');

INSERT INTO publisher (id, name) VALUES (1, 'Microsoft');

INSERT INTO game_systems (games_id, systems_id) VALUES (1, 1);

INSERT INTO game_developers (games_id, developers_id) VALUES (1, 1);

INSERT INTO game_publishers (games_id, publishers_id) VALUES (1, 1);

INSERT INTO game_ratings (game_id, ratings_id) VALUES (1, 1);
