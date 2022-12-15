package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)    //for extending HopEntity
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TransferwarehouseEntity extends HopEntity{
    @Column
    private String regionGeoJson;

    @Column
    private String logisticsPartner;

    @Column
    private String logisticsPartnerUrl;

}
