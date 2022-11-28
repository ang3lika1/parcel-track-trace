package at.fhtw.swen3;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import at.fhtw.swen3.persistence.entities.EntityValidator;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.*;
import at.fhtw.swen3.services.impl.*;
import at.fhtw.swen3.services.mapper.*;
import at.fhtw.swen3.services.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class SpringConfig {

    @Bean
    public EntityValidator getEntityValidator(){
        return new EntityValidator();
    }

    @Bean
    public Validator getValidator(){
        return new Validator();
    }

    @Bean
    public ParcelService getParcelService(ParcelMapper parcelMapper, Validator validator, ParcelRepository parcelRepository) {
        return new ParcelServiceImpl(parcelMapper, validator, parcelRepository);
    }

    @Bean
    public ParcelMapper getParcelMapper() {
        return new ParcelMapperImpl();
    }
    @Bean
    public HopArrivalMapper getHopArrivalMapper() {
        return new HopArrivalMapper();
    }

    @Bean
    public RecipientService getRecipientService(RecipientMapper recipientMapper, Validator validator, RecipientRepository recipientRepository) {
        return new RecipientServiceImpl(recipientMapper, validator, recipientRepository);
    }

    @Bean
    public RecipientMapper getRecipientMapper() {
        return new RecipientMapper();
    }

    @Bean
    public HopArrivalService getHopArrivalService(HopArrivalMapper hopArrivalMapper, Validator validator, HopArrivalRepository hopArrivalRepository) {
        return new HopArrivalServiceImpl(validator, hopArrivalMapper, hopArrivalRepository) {
        };
    }

    @Bean
    public HopArrivalMapper getHopArrivalMapper() {
        return new HopArrivalMapper();
    }
    @Bean
    public GeoCoordinateService getGeoCoordinateService(GeoCoordinateMapper geoCoordinateMapper, Validator validator, GeoCoordinateRepository geoCoordinateRepository) {
        return new GeoCoordinateServiceImpl(validator, geoCoordinateMapper, geoCoordinateRepository) {
        };
    }

    @Bean
    public GeoCoordinateMapper getGeoCoordinateMapper() {
        return new GeoCoordinateMapper();
    }

    @Bean
    public HopService getHopService(HopMapper hopMapper, Validator validator, HopRepository hopRepository) {
        return new HopServiceImpl(validator, hopMapper, hopRepository) {
        };
    }
    @Bean
    public HopMapper getHopMapper(GeoCoordinateMapper geoCoordinateMapper) {
        return new HopMapper(geoCoordinateMapper);
    }

    @Bean
    public TruckService getTruckService(TruckMapper truckMapper, Validator validator, TruckRepository truckRepository) {
        return new TruckServiceImpl(truckMapper, validator, truckRepository) {
        };
    }
    @Bean
    public TruckMapper getTruckMapper() {
        return new TruckMapper();
    }

    @Bean
    public WarehouseService getWarehouseService(WarehouseMapper warehouseMapper, Validator validator, WarehouseRepository warehouseRepository) {
        return new WarehouseServiceImpl(warehouseMapper, validator, warehouseRepository) {
        };
    }
    @Bean
    public WarehouseMapper getWarehouseMapper() {
        return new WarehouseMapper();
    }

}
