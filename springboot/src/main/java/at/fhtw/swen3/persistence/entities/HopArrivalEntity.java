package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

/**
 * HopArrival
 */


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "hop_arrival")
@RequiredArgsConstructor
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

