package companies.olx.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Defect {

    private CarModel carModel;
    private List<String> affectedYearsOfIssue;
    private String defectCode;

}
