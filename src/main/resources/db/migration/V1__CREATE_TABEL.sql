CREATE TABLE IF NOT EXISTS avatar (
    id SERIAL PRIMARY KEY,
    emoji VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    game_type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS profile (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    profile_photo VARCHAR(255),
    race_game_avatar_id BIGINT,
    balloon_game_avatar_id BIGINT,
    hangman_game_avatar_id BIGINT,
    FOREIGN KEY (race_game_avatar_id) REFERENCES avatar(id),
    FOREIGN KEY (balloon_game_avatar_id) REFERENCES avatar(id),
    FOREIGN KEY (hangman_game_avatar_id) REFERENCES avatar(id)
);

CREATE TABLE IF NOT EXISTS leaderboard (
    id SERIAL PRIMARY KEY,
    profile_id INTEGER,
    score INTEGER NOT NULL,
    game_type VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profile(id),
    UNIQUE (profile_id, game_type)
);