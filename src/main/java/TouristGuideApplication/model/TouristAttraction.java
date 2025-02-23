package TouristGuideApplication.model;

public class TouristAttraction {
    private String name;
    private String description;
    private String website;

    public TouristAttraction() {

    }

    public TouristAttraction(String name, String description, String website) {
        this.name = name;
        this.description = description;
        this.website = website;
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
}
