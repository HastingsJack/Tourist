package TouristGuideApplication.service;

import TouristGuideApplication.model.TouristAttraction;
import TouristGuideApplication.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public void addAttraction( String name, String description, String website) {
        touristRepository.addAttraction( name, description, website);
    }

    public void updateAttraction(TouristAttraction attraction) {
        touristRepository.updateAttraction(attraction);
    }

    public void deleteAttraction(TouristAttraction attraction) {
        touristRepository.deleteAttraction(attraction);
    }

    public List<String> getTagsForAttraction(String name) {
        return touristRepository.getTagsByName(name);
    }

    public List<String> getAllTags() {
        return touristRepository.getAllTags();
    }
}
