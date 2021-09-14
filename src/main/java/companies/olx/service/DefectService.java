package companies.olx.service;

import com.example.olxchallenge.model.CarModel;
import com.example.olxchallenge.model.Defect;

import java.util.List;

public interface DefectService {

    public List<Defect> locateDefectsInCars(List<CarModel> models);
}
