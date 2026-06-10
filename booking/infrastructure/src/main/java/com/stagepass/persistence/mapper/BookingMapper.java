package com.stagepass.persistence.mapper;

import com.stagepass.model.Booking;
import com.stagepass.persistence.BookingEntity;
import com.stagepass.shared.identity.BookingId;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.TicketId;
import com.stagepass.shared.identity.UserId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BookingMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "userId.value", target = "userId")
    @Mapping(source = "ticketId.value", target = "ticketId")
    @Mapping(source = "eventId.value", target = "eventId")
    @Mapping(source = "amount.amount", target = "amount")
    @Mapping(source = "amount.currency", target = "currency")
    BookingEntity toEntity(Booking booking);

    @Mapping(source = "id", target = "id", qualifiedByName = "toBookingId")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "toUserId")
    @Mapping(source = "ticketId", target = "ticketId", qualifiedByName = "toTicketId")
    @Mapping(source = "eventId", target = "eventId", qualifiedByName = "toEventId")
    @Mapping(source = "amount", target = "amount.amount")
    @Mapping(source = "currency", target = "amount.currency")
    Booking toDomain(BookingEntity bookingEntity);

    @Named("toBookingId")
    default BookingId toBookingId(String id) {
        return new BookingId(id);
    }

    @Named("toUserId")
    default UserId toUserId(String userId) {
        return new UserId(userId);
    }

    @Named("toEventId")
    default EventId toEventId(String eventId) {
        return new EventId(eventId);
    }

    @Named("toTicketId")
    default TicketId toTicketId(String ticketId) {
        return new TicketId(ticketId);
    }
}
