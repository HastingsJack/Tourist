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

        //uses rowmapper to read from attractionsTable:
        String sql = "SELECT id, name, description, website FROM attraction WHERE name LIKE ?";
        TouristAttraction touristAttraction = jdbcTemplate.queryForObject(sql, new AttractionRowMapper(), "%" + name + "%");


        //finds tags by joining attraction table with with attraction_by_tags
        String getTags =  """
                    SELECT tags.name 
                    FROM tags
                    INNER JOIN attraction_by_tags ON tags.id = attraction_by_tags.tagId 
                    INNER JOIN attraction ON attraction_by_tags.attractionId = attraction.id 
                    WHERE attraction.name = ? 
                """;

        String getCity = """
                    SELECT city.name 
                    FROM city
                    INNER JOIN attraction ON city.id = attraction.cityId 
                    WHERE attraction.name = ? 
                """;

        touristAttraction.setTags(jdbcTemplate.queryForList(getTags, String.class,name));
        touristAttraction.setCity(jdbcTemplate.queryForList(getCity, String.class, name));

        return touristAttraction;
    }

    public void addAttraction(TouristAttraction attraction) {
        String sql = "INSERT INTO attraction(name, description, website) VALUES (?,?,?)";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getWebsite());
    }

    //Is it better to disasemble attraction object here or in controller?
    public void updateAttraction(TouristAttraction attraction) {
        String sql = "UPDATE attraction SET name = ?, description = ?, website = ?, WHERE id = ? ";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(), attraction.getWebsite(), attraction.getId());

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

        /*

        //finds Primary key in attraction

        String getAttractionId = "SELECT id FROM attraction WHERE name = ?";
        Integer attractionId = jdbcTemplate.queryForObject(getAttractionId, Integer.class, name);


         */
        //find the tags
        String sql = """
                    SELECT tags.name 
                    FROM tags
                    INNER JOIN attraction_by_tags ON tags.id = attraction_by_tags.tagId 
                    INNER JOIN attraction ON attraction_by_tags.attractionId = attraction.id 
                    WHERE attraction.name = ? 
                """;

        return jdbcTemplate.queryForList(sql, String.class, name);

    }



    public List<String> getAllTags() {
        String sql = "SELECT name FROM tags";

        return jdbcTemplate.queryForList(sql,String.class);
    }

}
