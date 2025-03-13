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
        String sql = "SELECT id, name, description, website FROM attraction WHERE name LIKE ?";
        return jdbcTemplate.queryForObject(sql, new AttractionRowMapper(), "%" + name + "%");
    }


    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO attraction(name, description, website) VALUES (?,?,?)";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getWebsite());
    }


    //Is it better to disasemble attraction object here or in controller?
    public void updateAttraction(TouristAttraction attraction) {
        String sql = "UPDATE attraction SET name = ?, description = ?, website = ?, WHERE id = ? ";
        jdbcTemplate.update(sql, attraction.getName(),attraction.getDescription(), attraction.getWebsite(), attraction.getId());

//
//        TouristAttraction oldAttraction = getAttractionByName(attraction.getName());
//        oldAttraction.setDescription(attraction.getDescription());
//        oldAttraction.setWebsite(attraction.getWebsite());
    }

    public void deleteAttraction(TouristAttraction attraction) {
        String sql = "DELETE FROM attraction WHERE id = ? ";
        jdbcTemplate.update(sql, attraction.getId());
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
