package com.stagepass.persistence.mapper;

import com.stagepass.model.Ticket;
import com.stagepass.persistence.TicketEntity;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.VenueSeatId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TicketMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "eventId.value", target = "eventId")
    @Mapping(source = "venueSeatId.value", target = "venueSeatId")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "price.currency", target = "currency")
    TicketEntity toEntity(Ticket ticket);

    @Mapping(source = "id", target = "id", qualifiedByName = "toTicketId")
    @Mapping(source = "eventId", target = "eventId", qualifiedByName = "toEventId")
    @Mapping(source = "venueSeatId", target = "venueSeatId", qualifiedByName = "toVenueSeatId")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "currency", target = "price.currency")
    Ticket toDomain(TicketEntity entity);

    List<TicketEntity> toEntity(List<Ticket> ticket);

    List<Ticket> toDomain(List<TicketEntity> ticketEntity);

    @Named("toTicketId")
    default TicketId toTicketId(String id) {
        return new TicketId(id);
    }

    @Named("toEventId")
    default EventId toEventId(String id) {
        return new EventId(id);
    }

    @Named("toVenueSeatId")
    default VenueSeatId toVenueSeatId(String id) {
        return new VenueSeatId(id);
    }
}
