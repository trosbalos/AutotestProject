package mapper;

import hibernate.TripEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;
import tripDemo.model.Trip;
@Mapper(uses = TripMapper.class)
public interface TripMapper {
   TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mapping(target = "companyId", source = "company.id")
    Trip toDto(TripEntity tripEntity);

    @Mapping(target = "company.id", source = "companyId")
    TripEntity toEntity(Trip trip);
}