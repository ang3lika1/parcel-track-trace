package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.persistence.entity.HopEntity;
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
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private Integer traveltimeMins;
    @OneToOne
    private HopEntity hopEntity;

}
