package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;


/**
 * Recipient
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "recipient")
public class RecipientEntity {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long id;
  @Column
  private String name;
  @Column
  @Pattern(regexp = "[\\p{L}]+(\\s\\p{L}*[.]?)*\\s[0-9]*[\\p{L}]*((\\/)[0-9]*)*", message = "must match street name regex (Street, blank, number (number, slashes, characters)")
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

