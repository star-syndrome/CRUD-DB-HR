package org.metrodataacademy.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCountryRequest {

    private String id;
    private String name;
    private Integer regionID;
}