package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse_next_hop")
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private Integer traveltimeMins;
    @OneToOne(cascade = CascadeType.ALL)
    private HopEntity hop;
    @ManyToOne
    @JoinColumn(name="fk_warehouse", insertable = false, updatable = false)
    private WarehouseEntity warehouseSuper;
}
