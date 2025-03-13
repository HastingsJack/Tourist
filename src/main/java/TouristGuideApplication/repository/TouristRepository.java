package TouristGuideApplication.repository;

import TouristGuideApplication.Tags;
import TouristGuideApplication.model.AttractionRowMapper;
import TouristGuideApplication.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                System.getenv("DB_URL"),
                System.getenv("DB_USERNAME"),
                System.getenv("DB_PASSWORD")
        );
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<TouristAttraction> getAllAttractions() {
        String sql = "SELECT id, name, description, website FROM attraction";
        return jdbcTemplate.query(sql, new AttractionRowMapper());
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

    public List<String> getAllTags() {
        List<String> allTags = new ArrayList<>();
        for(Tags tag : Tags.values()) {
            allTags.add(tag.getDisplay());
        }
        return allTags;
    }

}
