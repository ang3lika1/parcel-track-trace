package at.fhtw.swen3;

import at.fhtw.swen3.persistence.entity.EntityValidator;
import at.fhtw.swen3.persistence.repository.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.Validator;
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
}
