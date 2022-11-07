package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecipientRepositoryTest {
    @Autowired
    private RecipientRepository recipientRepository;

    @Test
    public void should_store_a_recipient() {
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();

        RecipientEntity createdRecipient = recipientRepository.save(recipient);

        assertThat(createdRecipient).hasFieldOrPropertyWithValue("name", "recipientname");
        assertEquals(recipientRepository.count(), 1);
    }

    @Test
    void should_find_recipient_by_name() {
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();
        RecipientEntity sender = RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build();
        RecipientEntity sender2 = RecipientEntity.builder().name("sendername").street("Handelskai 300a").postalCode("A-1020").city("Wien").country("Austria").build();
        recipientRepository.save(recipient);
        recipientRepository.save(sender);
        recipientRepository.save(sender2);
        List<RecipientEntity> senders = new ArrayList<>();
        senders.add(sender);
        senders.add(sender2);

        List<RecipientEntity> foundRecipient = recipientRepository.findByName("sendername");

        assertThat(foundRecipient).isEqualTo(senders);
    }

    @Test
    public void should_delete_all_recipients() {
        RecipientEntity recipient = RecipientEntity.builder().name("recipientname").street("Landstraße 27a").postalCode("A-3500").city("Krems an der Donau").country("Austria").build();
        RecipientEntity sender = RecipientEntity.builder().name("sendername").street("Engerthstraße 228/6").postalCode("A-1020").city("Wien").country("Austria").build();
        RecipientEntity sender2 = RecipientEntity.builder().name("sendername").street("Handelskai 300a").postalCode("A-1020").city("Wien").country("Austria").build();

        recipientRepository.deleteAll();
        assertThat(recipientRepository.findAll()).isEmpty();
    }


}