package com.example.callingexternalapi.v1.integration.model.response;

import com.example.callingexternalapi.v1.integration.model.*;
import com.example.callingexternalapi.v1.integration.model.Row;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "destination_addresses",
        "origin_addresses",
        "rows",
        "status"
})
public class RoutesIntegrationResponse {

    @JsonProperty("destination_addresses")
    private List<String> destinationAddresses;
    @JsonProperty("origin_addresses")
    private List<String> originAddresses;
    @JsonProperty("rows")
    private List<Row> rows;
    @JsonProperty("status")
    private String status;

}
