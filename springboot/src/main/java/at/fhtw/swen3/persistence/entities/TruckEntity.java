package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)    //for extending HopEntity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "truck")
public class TruckEntity extends HopEntity{
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;*/
    @Column
    private String regionGeoJson;
    @Column
    private String numberPlate;
}
