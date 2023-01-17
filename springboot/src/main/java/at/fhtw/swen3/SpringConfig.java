package at.fhtw.swen3;

import at.fhtw.swen3.persistence.entities.EntityValidator;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.*;
import at.fhtw.swen3.services.impl.*;
import at.fhtw.swen3.services.mapper.*;
import at.fhtw.swen3.services.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public WarehouseNextHopsMapper getWareHouseNextHopsMapper() {
        return new WarehouseNextHopsMapper();
    }

    @Bean
    public HopMapper getHopMapper(GeoCoordinateMapper geoCoordinateMapper, WarehouseNextHopsMapper warehouseNextHopsMapper) {
        return new HopMapper(geoCoordinateMapper, warehouseNextHopsMapper);
    }

    @Bean
    public HopService getHopService(HopMapper hopMapper, Validator validator, HopRepository hopRepository) {
        return new HopServiceImpl(validator, hopMapper, hopRepository) {
        };
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
    public WarehouseService getWarehouseService(HopMapper hopMapper, WarehouseMapper warehouseMapper, Validator validator, WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, ResetService resetService) {
        return new WarehouseServiceImpl(hopMapper, warehouseMapper, validator, warehouseRepository, warehouseNextHopsRepository, resetService) {
        };
    }
    @Bean
    public WarehouseMapper getWarehouseMapper() {
        return new WarehouseMapper();
    }

    @Bean
    public ResetService getResetService(GeoCoordinateRepository geoCoordinateRepository, HopArrivalRepository hopArrivalRepository, HopRepository hopRepository, ParcelRepository parcelRepository, RecipientRepository recipientRepository, WarehouseNextHopsRepository warehouseNextHopsRepository){
        return new ResetServiceImpl(geoCoordinateRepository, hopArrivalRepository, hopRepository, parcelRepository, recipientRepository, warehouseNextHopsRepository);
    }

}
