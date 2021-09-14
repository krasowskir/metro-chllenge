package companies.olx.service;

import java.util.List;

public interface DefectService {

    public List<Defect> locateDefectsInCars(List<CarModel> models);
}
