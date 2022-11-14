package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;

public class RecipientMapper extends AbstractMapper<RecipientEntity, Recipient>{
    @Override
    public Recipient mapToTarget(RecipientEntity object) {
        return new Recipient(object.getName(), object.getStreet(), object.getPostalCode(), object.getCity(), object.getCountry());
    }

    @Override
    public RecipientEntity mapToSource(Recipient object) {
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
