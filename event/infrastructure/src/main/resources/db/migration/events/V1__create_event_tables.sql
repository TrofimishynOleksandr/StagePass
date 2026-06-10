CREATE TABLE event_events (
    id VARCHAR(36) PRIMARY KEY,
    venue_id VARCHAR(36) NOT NULL,
    name VARCHAR(100) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL
);