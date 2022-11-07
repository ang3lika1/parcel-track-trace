package at.fhtw.swen3.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * HopArrival
 */


@Data
@Entity
@Table(name = "hop_arrival")
public class HopArrivalEntity {
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

