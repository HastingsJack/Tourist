package TouristGuideApplication.repository;

import TouristGuideApplication.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        populateAttractions();
    }

    private void populateAttractions() {
        attractions.add(new TouristAttraction("Yellowstone","National Park","https://www.nps.gov/yell/index.htm"));
        attractions.add(new TouristAttraction("Shenandoah","National Park","https://www.nps.gov/shen/index.htm"));
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
        TouristAttraction oldAttraction = getAttractionById(attraction.getId());
        oldAttraction.setName(attraction.getName());
        oldAttraction.setDescription(attraction.getDescription());
        oldAttraction.setWebsite(attraction.getWebsite());
    }

    public TouristAttraction getAttractionById(UUID id) {
        for(TouristAttraction a : attractions) {
            if(a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public void deleteAttraction(TouristAttraction attraction) {
        attractions.remove(attraction);
    }


}
