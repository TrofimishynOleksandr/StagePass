package com.stagepass.persistence.mapper;

import com.stagepass.model.VenueSeat;
import com.stagepass.persistence.VenueSeatEntity;
import com.stagepass.shared.identity.VenueId;
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
public interface VenueSeatMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "venueId.value", target = "venueId")
    VenueSeatEntity toEntity(VenueSeat venueSeat);

    @Mapping(source = "id", target = "id", qualifiedByName = "toVenueSeatId")
    @Mapping(source = "venueId", target = "venueId", qualifiedByName = "toVenueId")
    VenueSeat toDomain(VenueSeatEntity entity);

    List<VenueSeatEntity> toEntity(List<VenueSeat> venueSeats);

    List<VenueSeat> toDomain(List<VenueSeatEntity> entities);

    @Named("toVenueSeatId")
    default VenueSeatId toVenueSeatId(String id) {
        return new VenueSeatId(id);
    }

    @Named("toVenueId")
    default VenueId toVenueId(String id) {
        return new VenueId(id);
    }
}