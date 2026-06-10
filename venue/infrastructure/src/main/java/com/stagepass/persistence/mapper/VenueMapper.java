package com.stagepass.persistence.mapper;

import com.stagepass.model.Venue;
import com.stagepass.persistence.VenueEntity;
import com.stagepass.shared.identity.VenueId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface VenueMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "address.street", target = "street")
    VenueEntity toEntity(Venue venue);

    @Mapping(source = "id", target = "id", qualifiedByName = "toVenueId")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "street", target = "address.street")
    Venue toDomain(VenueEntity entity);

    @Named("toVenueId")
    default VenueId toVenueId(String id) {
        return new VenueId(id);
    }
}