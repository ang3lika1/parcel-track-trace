package at.fhtw.swen3.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Recipient
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipient {
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
