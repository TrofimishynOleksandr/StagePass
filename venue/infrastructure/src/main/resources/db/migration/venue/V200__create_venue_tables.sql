CREATE TABLE venue_venues (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL
);

CREATE TABLE venue_seats (
    id VARCHAR(36) PRIMARY KEY,
    venue_id VARCHAR(36) NOT NULL,
    row INTEGER NOT NULL,
    seat_number INTEGER NOT NULL,
    section VARCHAR(20) NOT NULL
);