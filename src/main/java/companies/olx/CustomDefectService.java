package companies.olx;

import companies.olx.model.CarModel;
import companies.olx.model.Defect;
import companies.olx.service.DefectService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Coding Test

Before you start writing any code, discuss the implementation with the interviewers.

Objective: Assess how you work on a problem.

Advice: Take your time and work in a way consistent with your day to day job
Tasks

    Design data structures to support the following domain:
        car model (e.g. VW Golf), which is represented by the brand (one of BMW, VW or Audi), the model name and the version (which is numerical and always starts from 1)
        car, which is represented by the car model (e.g. VW Golf), the manufacturing year (e.g. 2001) and the engine serial number (e.g. "11A-12345")
        known defect, which is represented by the car model, the affected years of issue (e.g. 2001, 2002, 2003) and the defect code (e.g. "345F")
    Add support for the following operations on the car model:
        get a user-friendly text representation (for UI)
        get a developer-friendly text representation (for logs)
        increment the version
    Implement a method that accepts a list of cars and returns defects known to be present in them, given that there is a remote service that can be queried for defects of the provided car models

 */
public class CustomDefectService  {

//    private DefectService defectService;
//
//    public CustomDefectService(DefectService service) {
//        this.defectService = service;
//    }
//
//
//    public List<Defect> locateDefectsInCars(List<Car> cars) {
//        List<CarModel> models = cars.stream()
//                .map(Car::getCarModel)
//                .collect(Collectors.toList());
//
//        List<Defect> foundDefects = defectService.locateDefectsInCars(models);
//
//        List<Defect> filteredOutListOfDefects = new ArrayList<>();
//
//        for (Car car : cars){
//                    filteredOutListOfDefects.addAll(foundDefects.stream()
//                    .filter(d -> d.getAffectedYearsOfIssue().contains(car.getManufacturingYear()))
//                    .filter(d -> d.getCarModel().getBrand() == car.getCarModel().getBrand())
//                    .collect(Collectors.toSet()));
//        }
//        return filteredOutListOfDefects;
//    }


}
