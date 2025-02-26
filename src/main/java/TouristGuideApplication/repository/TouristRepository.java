package TouristGuideApplication.repository;

import TouristGuideApplication.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateAttractions();
    }

    private void populateAttractions() {
        attractions.add(new TouristAttraction("Yellowstone","National Park","https://www.nps.gov/yell/index.htm", List.of("NATUE", "ADVENTURE", "PAID", "HIKING"), List.of("Wyoming") ));
        attractions.add(new TouristAttraction("Shenandoah","National Park","https://www.nps.gov/shen/index.htm", List.of("NATUE", "ADVENTURE", "PAID"), List.of("Virginia")));
        attractions.add(new TouristAttraction("Apo Island","National Park","https://www.tripadvisor.dk/Tourism-g1074098-Apo_Island_Dauin_Negros_Oriental_Negros_Island_Visayas-Vacations.html", List.of("NATURE", "VULCANO", "ADVENTURE", "DIVING"), List.of("The Philippines")));
    }

    public List<TouristAttraction> getAllAttractions() {
        return attractions;
    }

    public TouristAttraction getAttractionByName(String name) {
        for (TouristAttraction a : attractions) {
            if(a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public void addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public void updateAttraction(TouristAttraction attraction) {
        TouristAttraction oldAttraction = getAttractionByName(attraction.getName());
        oldAttraction.setDescription(attraction.getDescription());
        oldAttraction.setWebsite(attraction.getWebsite());
    }

    public void deleteAttraction(TouristAttraction attraction) {
        attractions.remove(attraction);
    }

    public List<String> getTagsByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction.getTags();
            }
        }
        return new ArrayList<>();
    }
}
