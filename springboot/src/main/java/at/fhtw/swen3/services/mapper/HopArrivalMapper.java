package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Recipient;

public class HopArrivalMapper  extends AbstractMapper<HopArrivalEntity, HopArrival>{
    @Override
    public HopArrival mapToTarget(HopArrivalEntity object) {
        return HopArrival.builder().code(object.getCode()).description(object.getDescription()).dateTime(object.getDateTime()).build();
    }

    @Override
    public HopArrivalEntity mapToSource(HopArrival object) {
        return HopArrivalEntity.builder().code(object.getCode()).description(object.getDescription()).dateTime(object.getDateTime()).build();
    }
}
