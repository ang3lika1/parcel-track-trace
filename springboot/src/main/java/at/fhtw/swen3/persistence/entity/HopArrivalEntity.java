package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * HopArrival
 */


@Data
@Entity
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

