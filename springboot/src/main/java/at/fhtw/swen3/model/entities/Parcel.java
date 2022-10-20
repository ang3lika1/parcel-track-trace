package at.fhtw.swen3.model.entities;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Float weight;
    @OneToOne
    @JoinColumn(name="fk_recipient")
    private Recipient recipient;
    @OneToOne
    @JoinColumn(name="fk_sender")
    private Recipient sender;
    @Column
    private String trackingId;
    @Column
    private TrackingInformationDto.StateEnum deliveryStatus;
    @OneToMany
    private List<HopArrivalEntity> visitedHops;
    @OneToMany
    private List<HopArrivalEntity> futureHops;
}
