package org.metrodataacademy.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationRequest {

    private Integer id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
}