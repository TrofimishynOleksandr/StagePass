package com.stagepass.model;

import com.stagepass.exception.CancelCancelledTicketException;
import com.stagepass.exception.CancelSoldTicketException;
import com.stagepass.shared.domain.AggregateRoot;
import com.stagepass.shared.event.ticket.TicketCancelledEvent;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.VenueSeatId;
import com.stagepass.shared.model.Money;
import com.stagepass.shared.model.TicketStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class Ticket extends AggregateRoot {
    private final TicketId id;
    private final EventId eventId;
    private final VenueSeatId venueSeatId;
    private final Money price;
    @NonNull
    private TicketStatus status;

    public static Ticket create(EventId eventId, VenueSeatId venueSeatId,
                                Money price) {
        return new Ticket(
                TicketId.generate(),
                eventId,
                venueSeatId,
                price,
                TicketStatus.AVAILABLE);
    }

    public void cancel() {
        if (status == TicketStatus.CANCELLED) {
            throw new CancelCancelledTicketException(this.id);
        }
        else if (status == TicketStatus.SOLD) {
            throw new CancelSoldTicketException(this.id);
        }
        this.status = TicketStatus.CANCELLED;
        registerEvent(new TicketCancelledEvent(this.id, LocalDateTime.now()));
    }
//
//    public void sell() {
//        if (status == TicketStatus.CANCELLED) {
//            throw new SellCancelledTicketException(this.id);
//        }
//        else if (status == TicketStatus.SOLD) {
//            throw new SellSoldTicketException(this.id);
//        }
//        else if (status == TicketStatus.AVAILABLE) {
//            throw new SellAvailableTicketException(this.id);
//        }
//        this.status = TicketStatus.SOLD;
//        registerEvent(new TicketSoldEvent(this.id, LocalDateTime.now()));
//    }
}