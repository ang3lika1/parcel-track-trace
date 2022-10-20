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
    date = "2022-10-20T11:08:50+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public ParcelEntity from(ParcelDto parcel, NewParcelInfoDto newParcelInfo, TrackingInformationDto trackingInformation) {
        if ( parcel == null && newParcelInfo == null && trackingInformation == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        if ( parcel != null ) {
            parcelEntity.weight( parcel.getWeight() );
            parcelEntity.recipient( recipientDtoToRecipientEntity( parcel.getRecipient() ) );
            parcelEntity.sender( recipientDtoToRecipientEntity( parcel.getSender() ) );
        }
        if ( newParcelInfo != null ) {
            parcelEntity.trackingId( newParcelInfo.getTrackingId() );
        }
        if ( trackingInformation != null ) {
            parcelEntity.deliveryStatus( trackingInformation.getState() );
            parcelEntity.visitedHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getVisitedHops() ) );
            parcelEntity.futureHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getFutureHops() ) );
        }

        return parcelEntity.build();
    }

    @Override
    public ParcelDto toParcelDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ParcelDto.ParcelDtoBuilder parcelDto = ParcelDto.builder();

        parcelDto.weight( entity.getWeight() );
        parcelDto.recipient( recipientEntityToRecipientDto( entity.getRecipient() ) );
        parcelDto.sender( recipientEntityToRecipientDto( entity.getSender() ) );

        return parcelDto.build();
    }

    @Override
    public NewParcelInfoDto toParcelInfoDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        NewParcelInfoDto.NewParcelInfoDtoBuilder newParcelInfoDto = NewParcelInfoDto.builder();

        newParcelInfoDto.trackingId( entity.getTrackingId() );

        return newParcelInfoDto.build();
    }

    @Override
    public TrackingInformationDto toTrackingInfoDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TrackingInformationDto.TrackingInformationDtoBuilder trackingInformationDto = TrackingInformationDto.builder();

        trackingInformationDto.state( entity.getDeliveryStatus() );
        trackingInformationDto.visitedHops( hopArrivalEntityListToHopArrivalList( entity.getVisitedHops() ) );
        trackingInformationDto.futureHops( hopArrivalEntityListToHopArrivalList( entity.getFutureHops() ) );

        return trackingInformationDto.build();
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

        RecipientDto.RecipientDtoBuilder recipientDto = RecipientDto.builder();

        recipientDto.name( recipientEntity.getName() );
        recipientDto.street( recipientEntity.getStreet() );
        recipientDto.postalCode( recipientEntity.getPostalCode() );
        recipientDto.city( recipientEntity.getCity() );
        recipientDto.country( recipientEntity.getCountry() );

        return recipientDto.build();
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
