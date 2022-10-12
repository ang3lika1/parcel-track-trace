package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Pattern;


/**
 * Recipient
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipientEntity {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long id;
  @Column
  private String name;
  @Column
  @Pattern(regexp = "[a-zA-Z\\s]+(\\.\\s[a-zA-Z])?(\\s)[0-9]*[a-zA-Z]*((\\\\)[0-9]*)*", message = "must match street name regex (Street, blank, number (number, slashes, characters)")
  private String street;
  @Column
  @Pattern(regexp = "^\\bA-\\b[0-9]{4}$", message = "must match postal code regex (\"A-\", 4 digits, 0000-9999)")
  private String postalCode;
  @Column
  @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "must match city name regex e.g.San-Francisco")
  private String city;
  @Column
  private String country;
}

