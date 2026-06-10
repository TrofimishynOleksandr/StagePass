package com.stagepass.listener;

import com.stagepass.command.CancelTicketsCommand;
import com.stagepass.command.CreateTicketsCommand;
import com.stagepass.shared.event.event.EventCancelledEvent;
import com.stagepass.shared.event.event.EventCreatedEvent;
import com.stagepass.usecase.CancelTicketsUseCase;
import com.stagepass.usecase.CreateTicketsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketEventListener {
    private final CreateTicketsUseCase createTicketsUseCase;
    private final CancelTicketsUseCase cancelTicketsUseCase;

    @EventListener
    public void handle(EventCreatedEvent event) {
        CreateTicketsCommand command = new CreateTicketsCommand(
            event.eventId(),
            event.venueId(),
            event.priceBySection()
        );
        createTicketsUseCase.execute(command);
    }

    @EventListener
    public void handle(EventCancelledEvent event) {
        CancelTicketsCommand command = new CancelTicketsCommand(
                event.eventId()
        );
        cancelTicketsUseCase.execute(command);
    }
}
