package TouristGuideApplication;

public enum Tags {

    NATURE("Nature"),
    ADVENTURE("Adventure"),
    PAID("Paid"),
    HIKING("Hiking"),
    VULCANO("Vulcano"),
    DIVING("Diving");

    private String display;

    Tags(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
