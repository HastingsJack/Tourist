package TouristGuideApplication.model;

import java.util.List;

public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private String website;
    private List<String> tags;
    private List<String> city;

    public TouristAttraction() {

    }

    public TouristAttraction(int id, String name, String description, String website, List<String> tags, List<String> city) {
        this.name = name;
        this.description = description;
        this.website = website;
        this.tags = tags;
        this.city = city;
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getCity() {
        return city;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }
}
