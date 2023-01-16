package at.fhtw.swen3.persistence.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)    //for extending HopEntity
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseEntity extends HopEntity {
    @Column
    private Integer level;

    @OneToMany
    @JoinColumn(name="fk_warehouse")
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();
}
