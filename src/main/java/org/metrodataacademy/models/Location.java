package org.metrodataacademy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Integer id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private Country country;
}