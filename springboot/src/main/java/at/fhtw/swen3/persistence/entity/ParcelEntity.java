package at.fhtw.swen3.persistence.entity;


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
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    @DecimalMin(value = "0.1", message = "parcel weight must be over 0.0")
    private Float weight;
    @OneToOne
    @JoinColumn(name="fk_recipient")
    private RecipientEntity recipient;
    @OneToOne
    @JoinColumn(name="fk_sender")
    private RecipientEntity sender;
    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "must match regex (9 digits: upper case and numbers)")
    private String trackingId;
    @Column
    private TrackingInformationDto.StateEnum deliveryStatus;
    @OneToMany
    @NotNull
    private List<HopArrivalEntity> visitedHops;
    @OneToMany
    @NotNull
    private List<HopArrivalEntity> futureHops;
}
