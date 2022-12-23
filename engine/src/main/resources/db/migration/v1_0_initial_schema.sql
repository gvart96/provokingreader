CREATE TABLE IF NOT EXISTS subscriptions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title text NOT NULL,
    feed_url text NOT NULL,
    logo_url text
);
