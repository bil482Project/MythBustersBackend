INSERT INTO avatar (
    image_url,
    name,
    game_type
) VALUES (
    'url1',
    'car_avatar1',
    'car'
),
(
    'url2',
    'baloon_avatar1',
    'baloon'
),
(
    'url3',
    'hangman_avatar1',
    'hangman'
);

INSERT INTO profile (
    username,
    email,
    password_hash,
    profile_photo,
    race_game_avatar_id,
    baloon_game_avatar_id,
    hangman_game_avatar_id
) VALUES (
    'john_doe',
    'john.doe@example.com',
    '$2a$10$exampleHashedPassword1234567890',
    'http://example.com/profile_photo.jpg',
    1,
    2,
    3
);

INSERT INTO leaderboard (
    profile_id,
    score,
    game_type,
    date
) VALUES (
    1,
    500,
    'car',
    '2025-05-24 21:11:00'
);