package at.fhtw.swen3.persistence.entity;


import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.RecipientDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ParcelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private Float weight;
    @OneToOne
    @JoinColumn(name="fk_recipient")
    private RecipientEntity recipient;
    @OneToOne
    @JoinColumn(name="fk_sender")
    private RecipientEntity sender;
    @Column
    private String trackingId;
    @Column
    private TrackingInformationDto.StateEnum deliveryStatus;
    @OneToMany
    private List<HopArrivalEntity> visitedHops;
    @OneToMany
    private List<HopArrivalEntity> futureHops;
}
