package companies.olx;


import companies.olx.model.Defect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

//	public List<Defect> locateDefectsInCars(List<Car> cars){
//		//remote call
//		return new ArrayList<>();
//	}

}
