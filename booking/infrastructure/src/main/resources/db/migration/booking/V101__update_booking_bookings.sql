ALTER TABLE booking_bookings
    RENAME COLUMN seat_id TO ticket_id;

ALTER TABLE booking_bookings
    DROP COLUMN version;