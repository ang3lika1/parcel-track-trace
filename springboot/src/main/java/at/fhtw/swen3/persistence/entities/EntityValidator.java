package at.fhtw.swen3.persistence.entities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import javax.validation.*;
import java.util.Set;

@Slf4j
public class EntityValidator {

    static ValidatorFactory getValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }


    Validator getValidator() {
        return getValidatorFactory().getValidator();
    }

    <T> void validate(T o) {
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(o);
        violations.forEach(v -> log.error(v.getMessage()));
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
