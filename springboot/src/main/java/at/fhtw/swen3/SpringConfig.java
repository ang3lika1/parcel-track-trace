package at.fhtw.swen3;

import at.fhtw.swen3.persistence.entities.EntityValidator;
import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.HopArrivalService;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.impl.HopArrivalServiceImpl;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.validation.Validator;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.ParcelMapperImpl;
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
    public HopArrivalService getHopArrivalService(HopArrivalMapper hopArrivalMapper, Validator validator, HopArrivalRepository hopArrivalRepository) {
        return new HopArrivalServiceImpl(validator, hopArrivalMapper, hopArrivalRepository) {
        };
    }
    @Bean
    public HopArrivalMapper getHopArrivalMapper() {
        return new HopArrivalMapper();
    }
}
