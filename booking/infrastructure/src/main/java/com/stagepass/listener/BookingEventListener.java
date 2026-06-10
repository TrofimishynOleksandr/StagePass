package com.stagepass.listener;

import com.stagepass.port.TicketReservation;
import com.stagepass.shared.event.booking.BookingCancelledEvent;
import com.stagepass.shared.event.booking.BookingExpiredEvent;
import com.stagepass.shared.event.event.EventCancelledEvent;
import com.stagepass.usecase.CancelBookingsForEventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventListener {
    private final CancelBookingsForEventUseCase cancelBookingsUseCase;
    private final TicketReservation ticketReservation;

    @EventListener
    public void handle(EventCancelledEvent event) {
        cancelBookingsUseCase.execute(event.eventId());
    }

    @EventListener
    public void handle(BookingExpiredEvent event) {
        ticketReservation.release(event.ticketId());
    }

    /*
        listens not only CancelBookingUseCase,
        but also CancelBookingsForEventUseCase
        which doesn't need to release keys
    */
    @EventListener
    public void handle(BookingCancelledEvent event) {
        ticketReservation.release(event.ticketId());
    }
}
