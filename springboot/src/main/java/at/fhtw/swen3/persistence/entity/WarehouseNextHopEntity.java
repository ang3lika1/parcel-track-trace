package at.fhtw.swen3.persistence.entity;

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
public class WarehouseNextHopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private Integer traveltimeMins;
    @OneToOne
    private HopEntity hop;
    @ManyToOne
    @JoinColumn(name="fk_warehouse")
    private WarehouseEntity warehouse;

}
