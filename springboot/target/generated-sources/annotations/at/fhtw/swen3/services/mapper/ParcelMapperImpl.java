package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T14:22:37+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public ParcelEntity from(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation) {
        if ( parcel == null && newParcelInfo == null && trackingInformation == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        if ( parcel != null ) {
            parcelEntity.weight( parcel.getWeight() );
            parcelEntity.recipient( recipientToRecipientEntity( parcel.getRecipient() ) );
            parcelEntity.sender( recipientToRecipientEntity( parcel.getSender() ) );
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
    public Parcel toParcelDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Parcel.ParcelBuilder parcel = Parcel.builder();

        parcel.weight( entity.getWeight() );
        parcel.recipient( recipientEntityToRecipient( entity.getRecipient() ) );
        parcel.sender( recipientEntityToRecipient( entity.getSender() ) );

        return parcel.build();
    }

    @Override
    public NewParcelInfo toParcelInfoDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        NewParcelInfo.NewParcelInfoBuilder newParcelInfo = NewParcelInfo.builder();

        newParcelInfo.trackingId( entity.getTrackingId() );

        return newParcelInfo.build();
    }

    @Override
    public TrackingInformation toTrackingInfoDto(ParcelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        TrackingInformation.TrackingInformationBuilder trackingInformation = TrackingInformation.builder();

        trackingInformation.state( entity.getDeliveryStatus() );
        trackingInformation.visitedHops( hopArrivalEntityListToHopArrivalList( entity.getVisitedHops() ) );
        trackingInformation.futureHops( hopArrivalEntityListToHopArrivalList( entity.getFutureHops() ) );

        return trackingInformation.build();
    }

    protected RecipientEntity recipientToRecipientEntity(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }

        RecipientEntity.RecipientEntityBuilder recipientEntity = RecipientEntity.builder();

        recipientEntity.name( recipient.getName() );
        recipientEntity.street( recipient.getStreet() );
        recipientEntity.postalCode( recipient.getPostalCode() );
        recipientEntity.city( recipient.getCity() );
        recipientEntity.country( recipient.getCountry() );

        return recipientEntity.build();
    }

    protected HopArrivalEntity hopArrivalToHopArrivalEntity(HopArrival hopArrival) {
        if ( hopArrival == null ) {
            return null;
        }

        HopArrivalEntity.HopArrivalEntityBuilder hopArrivalEntity = HopArrivalEntity.builder();

        hopArrivalEntity.code( hopArrival.getCode() );
        hopArrivalEntity.description( hopArrival.getDescription() );
        hopArrivalEntity.dateTime( hopArrival.getDateTime() );

        return hopArrivalEntity.build();
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

    protected Recipient recipientEntityToRecipient(RecipientEntity recipientEntity) {
        if ( recipientEntity == null ) {
            return null;
        }

        Recipient.RecipientBuilder recipient = Recipient.builder();

        recipient.name( recipientEntity.getName() );
        recipient.street( recipientEntity.getStreet() );
        recipient.postalCode( recipientEntity.getPostalCode() );
        recipient.city( recipientEntity.getCity() );
        recipient.country( recipientEntity.getCountry() );

        return recipient.build();
    }

    protected HopArrival hopArrivalEntityToHopArrival(HopArrivalEntity hopArrivalEntity) {
        if ( hopArrivalEntity == null ) {
            return null;
        }

        HopArrival.HopArrivalBuilder hopArrival = HopArrival.builder();

        hopArrival.code( hopArrivalEntity.getCode() );
        hopArrival.description( hopArrivalEntity.getDescription() );
        hopArrival.dateTime( hopArrivalEntity.getDateTime() );

        return hopArrival.build();
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
