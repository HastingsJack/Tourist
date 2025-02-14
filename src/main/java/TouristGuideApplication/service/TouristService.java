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

    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    public void updateAttraction(TouristAttraction attraction) {
        touristRepository.updateAttraction(attraction);
    }

    public TouristAttraction getAttractionById(UUID id) {
        return touristRepository.getAttractionById(id);
    }

    public void deleteAttraction(TouristAttraction attraction) {
        touristRepository.deleteAttraction(attraction);
    }
}
