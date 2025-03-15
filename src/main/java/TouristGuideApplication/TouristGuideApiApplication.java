package TouristGuideApplication;

import TouristGuideApplication.repository.TouristRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TouristGuideApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideApiApplication.class, args);

        TouristRepository repository = new TouristRepository();
        System.out.println(repository.getAllAttractions());
    }



}
