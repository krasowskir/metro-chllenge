package companies.olx.model;

import java.util.List;

public class Defect {

    private CarModel carModel;
    private List<String> affectedYearsOfIssue;
    private String defectCode;

    public companies.olx.model.CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(companies.olx.model.CarModel carModel) {
        this.carModel = carModel;
    }

    public List<String> getAffectedYearsOfIssue() {
        return affectedYearsOfIssue;
    }

    public void setAffectedYearsOfIssue(List<String> affectedYearsOfIssue) {
        this.affectedYearsOfIssue = affectedYearsOfIssue;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }
}
