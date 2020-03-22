INSERT INTO game (id, title) VALUES (1, 'Halo: Combat Evolved');

INSERT INTO game_mapper (id, database, ref, url, game_id) VALUES (1, 'IGDB', 740, 'https://www.igdb.com/games/halo-combat-evolved', 1);

INSERT INTO platform (id, name) VALUES (1, 'Xbox');

INSERT INTO platform_mapper (id, database, ref, url, platform_id) VALUES (1, 'IGDB', 11, 'https://www.igdb.com/platforms/xbox', 1);

INSERT INTO profile (id, name) VALUES (1, 'Test User');

INSERT INTO priority (id, name) VALUES (1, 'High');
INSERT INTO priority (id, name) VALUES (2, 'Medium');
INSERT INTO priority (id, name) VALUES (3, 'Low');

INSERT INTO status (id, name) VALUES (1, 'Unplayed');
INSERT INTO status (id, name) VALUES (2, 'Unreleased');
INSERT INTO status (id, name) VALUES (3, 'Played');
INSERT INTO status (id, name) VALUES (4, 'Played Majority');

INSERT INTO game_profile (id, game_id, profile_id, last_played_date, priority_id, status_id) VALUES (1, 1, 1, '2020-1-1', 1, 4);

INSERT INTO game_profile_platforms (game_profile_id, platforms_id) VALUES (1, 1);
