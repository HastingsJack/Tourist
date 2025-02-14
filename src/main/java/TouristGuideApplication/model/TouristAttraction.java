package TouristGuideApplication.model;

import java.util.UUID;

public class TouristAttraction {
    private String name;
    private String description;
    private String website;
    private UUID id;

    public TouristAttraction() {

    }

    public TouristAttraction(String name, String description, String website) {
        this.name = name;
        this.description = description;
        this.website = website;
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
