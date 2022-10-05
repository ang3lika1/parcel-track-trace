package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


/**
 * Recipient
 */

@Data
@Entity
public class RecipientEntity {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private Long id;
  @Column
  private String name;
  @Column
  private String street;
  @Column
  private String postalCode;
  @Column
  private String city;
  @Column
  private String country;
}

