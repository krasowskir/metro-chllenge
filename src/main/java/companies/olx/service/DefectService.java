package companies.olx.service;

import companies.olx.model.CarModel;
import companies.olx.model.Defect;

import java.util.List;

public interface DefectService {

    public List<Defect> locateDefectsInCars(List<CarModel> models);
}
