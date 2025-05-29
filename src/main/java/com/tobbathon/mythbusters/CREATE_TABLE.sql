-- 1. Kullanıcı Profili Tablosu
CREATE TABLE profiles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    profile_photo TEXT
);

-- 2. Oyunlar Tablosu
CREATE TABLE games (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- 3. Avatarlar Tablosu
CREATE TABLE avatars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    image_url TEXT
);

-- 4. Skorlar Tablosu (Her kullanıcı için oyun başına en yüksek skor)
CREATE TABLE high_scores (
    id SERIAL PRIMARY KEY,
    profile_id INT REFERENCES profiles(id) ON DELETE CASCADE,
    game_id INT REFERENCES games(id) ON DELETE CASCADE,
    score INT NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    avatar_id INT REFERENCES avatars(id) ON DELETE SET NULL,
    UNIQUE(profile_id, game_id)  -- Her kullanıcı, her oyun için yalnızca 1 skor kaydı
);

INSERT INTO profiles (name, profile_photo) VALUES ('Ali', 'ali.jpg'), ('Ayşe', 'ayse.jpg');

INSERT INTO games (name) VALUES ('Game1'), ('Game2'), ('Game3');

INSERT INTO avatars (name, image_url) VALUES ('Robot', 'robot.png'), ('Kedi', 'kedi.png');
