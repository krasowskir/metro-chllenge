package com.example.olxchallenge.mock;

import com.example.olxchallenge.model.Brand;
import com.example.olxchallenge.model.CarModel;
import com.example.olxchallenge.model.Defect;
import com.example.olxchallenge.service.DefectService;

import java.util.Arrays;
import java.util.List;

public class DefectsMock implements DefectService {

    @Override
    public List<Defect> locateDefectsInCars(List<CarModel> models) {

        Defect audiDefect = new Defect();
        audiDefect.setDefectCode("345F");
        audiDefect.setAffectedYearsOfIssue(Arrays.asList("2002", "2003"));
        CarModel audiModel = new CarModel();
        audiModel.setBrand(Brand.AUDI);
        audiModel.setModelName("A3");

        audiDefect.setCarModel(audiModel);

        Defect bmwDefect = new Defect();
        bmwDefect.setDefectCode("123F");
        bmwDefect.setAffectedYearsOfIssue(Arrays.asList("2000", "2001", "2010"));
        CarModel bmwModel = new CarModel();
        bmwModel.setBrand(Brand.BMW);
        bmwModel.setModelName("M5");

        bmwDefect.setCarModel(bmwModel);

        return Arrays.asList(audiDefect, bmwDefect);
    }

}
