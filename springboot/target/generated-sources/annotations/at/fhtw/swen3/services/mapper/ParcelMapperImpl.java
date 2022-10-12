package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.RecipientDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-11T09:57:50+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public ParcelEntity from(ParcelDto parcel, NewParcelInfoDto newParcelInfo, TrackingInformationDto trackingInformation) {
        if ( parcel == null && newParcelInfo == null && trackingInformation == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        if ( parcel != null ) {
            parcelEntity.setWeight( parcel.getWeight() );
            parcelEntity.setRecipient( recipientDtoToRecipientEntity( parcel.getRecipient() ) );
            parcelEntity.setSender( recipientDtoToRecipientEntity( parcel.getSender() ) );
        }
        if ( newParcelInfo != null ) {
            parcelEntity.setTrackingId( newParcelInfo.getTrackingId() );
        }
        if ( trackingInformation != null ) {
            parcelEntity.setDeliveryStatus( trackingInformation.getState() );
            parcelEntity.setVisitedHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getVisitedHops() ) );
            parcelEntity.setFutureHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getFutureHops() ) );
        }

        return parcelEntity;
    }

    protected RecipientEntity recipientDtoToRecipientEntity(RecipientDto recipientDto) {
        if ( recipientDto == null ) {
            return null;
        }

        RecipientEntity.RecipientEntityBuilder recipientEntity = RecipientEntity.builder();

        recipientEntity.name( recipientDto.getName() );
        recipientEntity.street( recipientDto.getStreet() );
        recipientEntity.postalCode( recipientDto.getPostalCode() );
        recipientEntity.city( recipientDto.getCity() );
        recipientEntity.country( recipientDto.getCountry() );

        return recipientEntity.build();
    }

    protected HopArrivalEntity hopArrivalToHopArrivalEntity(HopArrival hopArrival) {
        if ( hopArrival == null ) {
            return null;
        }

        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();

        hopArrivalEntity.setCode( hopArrival.getCode() );
        hopArrivalEntity.setDescription( hopArrival.getDescription() );
        hopArrivalEntity.setDateTime( hopArrival.getDateTime() );

        return hopArrivalEntity;
    }

    protected List<HopArrivalEntity> hopArrivalListToHopArrivalEntityList(List<HopArrival> list) {
        if ( list == null ) {
            return null;
        }

        List<HopArrivalEntity> list1 = new ArrayList<HopArrivalEntity>( list.size() );
        for ( HopArrival hopArrival : list ) {
            list1.add( hopArrivalToHopArrivalEntity( hopArrival ) );
        }

        return list1;
    }
}
