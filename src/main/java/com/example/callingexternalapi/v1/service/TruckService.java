package com.example.callingexternalapi.v1.service;

import com.example.callingexternalapi.v1.integration.RoutesIntegration;
import com.example.callingexternalapi.v1.integration.model.mapper.RoutesIntegrationMapper;
import com.example.callingexternalapi.v1.integration.model.request.RoutesIntegrationRequest;
import com.example.callingexternalapi.v1.model.truck.TruckMapper;
import com.example.callingexternalapi.v1.model.truck.TruckRequest;
import com.example.callingexternalapi.v1.model.truck.TruckResponse;
import com.example.callingexternalapi.v1.repository.TruckRepository;
import com.example.callingexternalapi.v1.repository.entity.TruckEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TruckService {

    private TruckRepository truckRepository;
    private RoutesIntegration routesIntegration;

    public List<TruckResponse> getTrucksByIdOrAll(List<String> ids){

        if(ids.isEmpty()){
            return this.truckRepository.findAll().stream().map(TruckMapper::truckEntityToResponse).toList();
        }else{
            return this.truckRepository.findAllByIdIn(ids).stream().map(TruckMapper::truckEntityToResponse).toList();
        }
    }

    public TruckResponse createTruck(TruckRequest truckRequest) {
        TruckEntity truckCreate=TruckMapper.truckRequestoToEntity(truckRequest);

        return TruckMapper.truckEntityToResponse(this.truckRepository.save(truckCreate));
    }

    public void deleteTruckByIdOrAll(List<String> codigos) {

        if (codigos.isEmpty()) {
            this.truckRepository.deleteAll();
        } else {
            this.truckRepository.findAllByIdIn(codigos)
                    .stream()
                    .map(TruckEntity::getId)
                    .toList()
                    .forEach(value -> truckRepository.deleteById(value));                                              ;
        }
    }
    public TruckResponse updateDataById(String id, TruckRequest truckRequest) {
        TruckEntity truckToSave = this.truckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Truck not found by ID"));

        truckRequest.setId(truckToSave.getId());
        truckRepository.save(TruckMapper.truckRequestoToEntity(truckRequest));
        return TruckMapper.truckRequestToResponse(truckRequest);
    }

    public TruckResponse updateTrucksRoute(String id, RoutesIntegrationRequest routesIntegrationRequest){
        var truckToSave = this.truckRepository.findById(id).get();
        truckToSave.setId(id);
        truckToSave.setRoutesEntity(RoutesIntegrationMapper.routeIntegrationResponseToRouteResponse(routesIntegration.getRoutes(routesIntegrationRequest)));
        this.truckRepository.save(truckToSave);

        return TruckMapper.truckEntityToResponse(truckToSave);
    }

    public void deleteRouteTruckById(String id){
        var truckToDeleteRoute = this.truckRepository.findById(id).get();
        truckToDeleteRoute.setId(id);
        truckToDeleteRoute.setRoutesEntity(null);
        this.truckRepository.save(truckToDeleteRoute);

    }

}
