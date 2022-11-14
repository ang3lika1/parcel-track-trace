package at.fhtw.swen3.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
  @Pattern(regexp = " ^[A-Z]{4}\\d{1,4}$", message = "must match postal code regex")
  private String code;
  @Column
  @Pattern(regexp = " ^[A-Za-z0-9\\s\\-]$", message = "must match postal code regex")
  private String description;
  @Column
  private OffsetDateTime dateTime;
  @ManyToOne
  private ParcelEntity parcel;
}

