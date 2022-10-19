package at.fhtw.swen3.model.entities;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HopArrival {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String code;
    @Column
    private String description;
    @Column
    private OffsetDateTime dateTime;
    @ManyToOne
    private ParcelEntity parcel;
}
