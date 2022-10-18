package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entity.EntityValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.xml.validation.Validator;

@Configuration
public class ValidatorConfig {

    @Bean
    public EntityValidator validator(){
        return new EntityValidator();
    }
}
