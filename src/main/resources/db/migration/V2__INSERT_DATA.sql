INSERT INTO avatar (
    emoji,
    name,
    game_type
) VALUES (
    '🚙',
    'car_avatar1',
    'car'
),
(
    '🏍️',
    'car_avatar2',
    'car'
),
(
    '🏎🏍️',
    'car_avatar3',
    'car'
),
(
    'url1',
    'balloon_avatar1',
    'balloon'
),
(
    'url2',
    'hangman_avatar1',
    'hangman'
);

INSERT INTO profile (
    username,
    email,
    password_hash,
    profile_photo,
    race_game_avatar_id,
    balloon_game_avatar_id,
    hangman_game_avatar_id,
    coin
) VALUES
    ('john_doe', 'john.doe@example.com', '$2a$10$exampleHashedPassword1234567890', 'http://example.com/john_profile.jpg', 1, 3, 4, 400),
    ('jane_smith', 'jane.smith@example.com', '$2a$10$anotherHashedPassword0987654321', 'http://example.com/jane_profile.jpg', 2, 3, 4, 200),
    ('alice_wong', 'alice.wong@example.com', '$2a$10$hashedPasswordAlice123456789', 'http://example.com/alice_profile.jpg', 2, 3, 4, 20),
    ('bob_jones', 'bob.jones@example.com', '$2a$10$hashedPasswordBob9876543210', 'http://example.com/bob_profile.jpg', 1, 3, 4, 0);

INSERT INTO leaderboard (
    profile_id,
    score,
    game_type,
    date
) VALUES
    (1, 500, 'car', '2025-05-24 21:11:00'),
    (2, 750, 'car', '2025-05-25 10:30:00'),
    (1, 300, 'balloon', '2025-05-24 22:15:00'),
    (3, 600, 'hangman', '2025-05-26 14:20:00'),
    (4, 450, 'car', '2025-05-27 09:45:00'),
    (2, 800, 'balloon', '2025-05-27 11:00:00');