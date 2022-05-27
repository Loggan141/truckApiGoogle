package com.example.callingexternalapi.v1.integration;

import com.example.callingexternalapi.v1.integration.model.request.RoutesIntegrationRequest;
import com.example.callingexternalapi.v1.integration.model.response.RoutesIntegrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor

public class RoutesIntegration {
	private static final String API_KEY = "AIl4DAul3lO5nKPNdURmO-Es";

	@Autowired
	private final RestTemplate restTemplate;

    public RoutesIntegrationResponse getRoutes(RoutesIntegrationRequest routesIntegrationRequest){

	//https://maps.googleapis.com/maps/api/distancematrix/json?origins=CANOAS
		// &destinations=MARAU&units=imperial&key=AIzaSyBHPJ4BG_anKPNdURmO-Es

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("maps.googleapis.com")
				.path("/maps/api/distancematrix/json")
				.queryParam("origins", routesIntegrationRequest.getOriginAddresses())
				.queryParam("destinations", routesIntegrationRequest.getDestinationAddresses())
				.queryParam("units","metric")
                .queryParam("key", API_KEY)
                .build();


		RoutesIntegrationResponse routesIntegrationResponse = restTemplate.getForObject(uri.toUriString(), RoutesIntegrationResponse.class);

        return routesIntegrationResponse;
    }
}

















