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
    date = "2022-10-18T13:22:56+0200",
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

    @Override
    public ParcelDto toParcelDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ParcelDto parcelDto = new ParcelDto();

        parcelDto.setWeight( entity.getWeight() );
        parcelDto.setRecipient( recipientEntityToRecipientDto( entity.getRecipient() ) );
        parcelDto.setSender( recipientEntityToRecipientDto( entity.getSender() ) );

        return parcelDto;
    }

    @Override
    public NewParcelInfoDto toParcelInfoDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        NewParcelInfoDto newParcelInfoDto = new NewParcelInfoDto();

        newParcelInfoDto.setTrackingId( entity.getTrackingId() );

        return newParcelInfoDto;
    }

    @Override
    public TrackingInformationDto toTrackingInfoDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TrackingInformationDto trackingInformationDto = new TrackingInformationDto();

        trackingInformationDto.setState( entity.getDeliveryStatus() );
        trackingInformationDto.setVisitedHops( hopArrivalEntityListToHopArrivalList( entity.getVisitedHops() ) );
        trackingInformationDto.setFutureHops( hopArrivalEntityListToHopArrivalList( entity.getFutureHops() ) );

        return trackingInformationDto;
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

    protected RecipientDto recipientEntityToRecipientDto(RecipientEntity recipientEntity) {
        if ( recipientEntity == null ) {
            return null;
        }

        String name = null;
        String street = null;
        String postalCode = null;
        String city = null;
        String country = null;

        name = recipientEntity.getName();
        street = recipientEntity.getStreet();
        postalCode = recipientEntity.getPostalCode();
        city = recipientEntity.getCity();
        country = recipientEntity.getCountry();

        RecipientDto recipientDto = new RecipientDto( name, street, postalCode, city, country );

        return recipientDto;
    }

    protected HopArrival hopArrivalEntityToHopArrival(HopArrivalEntity hopArrivalEntity) {
        if ( hopArrivalEntity == null ) {
            return null;
        }

        HopArrival hopArrival = new HopArrival();

        hopArrival.setCode( hopArrivalEntity.getCode() );
        hopArrival.setDescription( hopArrivalEntity.getDescription() );
        hopArrival.setDateTime( hopArrivalEntity.getDateTime() );

        return hopArrival;
    }

    protected List<HopArrival> hopArrivalEntityListToHopArrivalList(List<HopArrivalEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<HopArrival> list1 = new ArrayList<HopArrival>( list.size() );
        for ( HopArrivalEntity hopArrivalEntity : list ) {
            list1.add( hopArrivalEntityToHopArrival( hopArrivalEntity ) );
        }

        return list1;
    }
}
