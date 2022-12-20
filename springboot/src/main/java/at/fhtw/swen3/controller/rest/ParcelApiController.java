package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.HopArrivalService;
import at.fhtw.swen3.services.HopService;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.sql.SQLException;
import java.util.Optional;
import javax.annotation.Generated;

@RequiredArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-22T13:39:57.022856Z[Etc/UTC]")
@Controller
@Slf4j
public class ParcelApiController implements ParcelApi {

    private final ParcelService parcelService;
    private final HopService hopService;
    private final HopArrivalService hopArrivalService;
    private final NativeWebRequest request;


   @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
         NewParcelInfo submittedParcelInfo = parcelService.saveNewParcel(parcel);
         return new ResponseEntity<>(submittedParcelInfo, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
       ParcelEntity parcel = null;
       HopEntity hop = null;
        try {
            parcel = parcelService.getParcel(trackingId);
            hop = hopService.getHop(code);
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (hop == null || parcel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HopArrivalEntity hopArrival = HopArrivalEntity.builder().code(code).parcel(parcel).description("").build();

        try {
            parcelService.changeHopArrival(parcel, hopArrival, hop);
        } catch (DataAccessException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        ParcelEntity parcelEntity;
        try {
            parcelEntity = parcelService.reportParcelDelivery(trackingId);
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(parcelEntity==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
       ResponseEntity<NewParcelInfo> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            response = parcelService.saveExistingParcel(trackingId, parcel);
        } catch (SQLException e) {
            log.warn(e.getMessage());
            return response;
        }

        return response;
    }
}
