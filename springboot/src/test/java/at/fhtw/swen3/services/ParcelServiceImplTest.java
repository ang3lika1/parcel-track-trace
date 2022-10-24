package at.fhtw.swen3.services;

import at.fhtw.swen3.SpringConfig;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.impl.ParcelApiController;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@EnableJpaRepositories(basePackages="at.fhtw.swen3.persistence.repository", entityManagerFactoryRef="entityManagerFactory")
class ParcelServiceImplTest {

    private @Mock
    ParcelDto parcelDto;
    @Autowired
    private ParcelService parcelService;

    @Test
    void saveNewParcel() {
        //AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        //ctx.register(SpringConfig.class);
        //ctx.refresh();

        //ParcelService parcelService = ctx.getBean(ParcelService.class);
        parcelService.saveNewParcel(parcelDto);

        //ctx.close();
    }
}