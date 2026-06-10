package com.stagepass.persistence.mapper;

import com.stagepass.model.Event;
import com.stagepass.persistence.EventEntity;
import com.stagepass.shared.identity.EventId;
import com.stagepass.shared.identity.VenueId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EventMapper {

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "venueId.value", target = "venueId")
    EventEntity toEntity(Event event);

    @Mapping(source = "id", target = "id", qualifiedByName = "toEventId")
    @Mapping(source = "venueId", target = "venueId", qualifiedByName = "toVenueId")
    Event toDomain(EventEntity entity);

    @Named("toEventId")
    default EventId toEventId(String id) {
        return new EventId(id);
    }

    @Named("toVenueId")
    default VenueId toVenueId(String id) {
        return new VenueId(id);
    }
}
