package com.example.olxchallenge;

import com.example.olxchallenge.mock.DefectsMock;
import com.example.olxchallenge.model.Brand;
import com.example.olxchallenge.model.Car;
import com.example.olxchallenge.model.CarModel;
import com.example.olxchallenge.model.Defect;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CustomDefectServiceTest {

    @Test
    public void testLocateDefectsInCars(){
        CarModel audiModel = new CarModel();
        audiModel.setBrand(Brand.AUDI);
        audiModel.setModelName("A3");

        Car audi = new Car();
        audi.setEngineSerialNumber("11A-12345");
        audi.setManufacturingYear("2001");
        audi.setCarModel(audiModel);

        CarModel vwModel = new CarModel();
        vwModel.setBrand(Brand.VW);
        vwModel.setModelName("Passar");

        Car vw = new Car();
        vw.setEngineSerialNumber("11A-98765");
        vw.setManufacturingYear("2002");
        vw.setCarModel(vwModel);

        CarModel bmwModel = new CarModel();
        audiModel.setBrand(Brand.BMW);
        audiModel.setModelName("M5");

        Car bmw = new Car();
        bmw.setEngineSerialNumber("11A-00000");
        bmw.setManufacturingYear("2000");
        bmw.setCarModel(bmwModel);

        List<Car> carsToBeTested = Arrays.asList(audi, vw, bmw);

        DefectsMock mockedService = new DefectsMock();
        CustomDefectService defectService = new CustomDefectService(mockedService);

        List<Defect> foundDefects = defectService.locateDefectsInCars(carsToBeTested);
        assert foundDefects.size() != 0;
        assert foundDefects.size() == 2;
    }

}