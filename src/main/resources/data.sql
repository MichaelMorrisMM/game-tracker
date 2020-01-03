INSERT INTO game (id, title) VALUES (1, 'Halo: Combat Evolved');

INSERT INTO platform (id, name) VALUES (1, 'Xbox');

INSERT INTO company (id, name) VALUES (1, 'Bungie');
INSERT INTO company (id, name) VALUES (2, 'Microsoft');

INSERT INTO game_platform (id, game_id, platform_id, release_date, region) VALUES (1, 1, 1, '2001-11-15', 'NORTH_AMERICA');

INSERT INTO game_companies (games_id, companies_id) VALUES (1, 1);

INSERT INTO game_age_ratings (game_id, age_rating) VALUES (1, 'ESRB_M');

INSERT INTO profile (id, name) VALUES (1, 'Test User');

INSERT INTO game_profile (id, game_id, profile_id, last_played_date, priority, status) VALUES (1, 1, 1, '2020-1-1', 'HIGH', 'PLAYED_MAJORITY');

INSERT INTO game_profile_platforms (game_profile_id, platforms_id) VALUES (1, 1);
