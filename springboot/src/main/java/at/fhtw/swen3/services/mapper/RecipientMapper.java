package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.RecipientDto;

public class RecipientMapper extends AbstractMapper<RecipientEntity, RecipientDto>{
    @Override
    public RecipientDto mapToTarget(RecipientEntity object) {
        return new RecipientDto(object.getName(), object.getStreet(), object.getPostalCode(), object.getCity(), object.getCountry());
    }

    @Override
    public RecipientEntity mapToSource(RecipientDto object) {
        return RecipientEntity.builder()
                .name(object.getName())
                .street(object.getStreet())
                .postalCode(object.getPostalCode())
                .city(object.getCity())
                .country(object.getCountry())
                .build();
    }

    //use Builder or AllArgsConstructor?
    //set id of RecipientEntity in RecipientDto during mapping?
}
