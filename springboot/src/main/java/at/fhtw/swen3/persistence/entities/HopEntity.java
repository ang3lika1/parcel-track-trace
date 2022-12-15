package at.fhtw.swen3.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hop")
@Inheritance
public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String hopType;

    @Column
    @NotNull @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;

    @Column
    private String description;

    @Column
    private Integer processingDelayMins;

    @Column
    private String locationName;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull @Valid
    private GeoCoordinateEntity locationCoordinates;

}
