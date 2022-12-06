package at.fhtw.swen3.persistence.entities;


import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parcel")
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    @DecimalMin(value = "0.1", message = "parcel weight must be over 0.0")
    private Float weight;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_recipient")
    @NotNull
    private RecipientEntity recipient;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_sender")
    @NotNull
    private RecipientEntity sender;
    @Column
    private String trackingId;
    @Column
    private TrackingInformation.StateEnum deliveryStatus;
    @OneToMany
    private List<HopArrivalEntity> visitedHops;
    @OneToMany
    private List<HopArrivalEntity> futureHops;

    public void addVisitedHop(HopArrivalEntity hopArrival) {
        visitedHops.add(hopArrival);
    }

    public void removeFutureHop(HopArrivalEntity hopArrival) {
        futureHops.remove(hopArrival);
    }
}
