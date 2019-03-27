package travelcompany.city.domain;

public enum Climate {
    POLAR("cold summer, cold winter"),
    CONTINENTAL("warm summer, cold winter"),
    TEMPERATE("warm summer, warm winter"),
    DRY("hot summer, warm winter"),
    TROPICAL("hot summer, hot winter");

    private String description;

    public String getDescription() {
        return description;
    }

    Climate(String description) {
        this.description = description;

    }
}
