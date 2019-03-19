package hw2.travelcompany.city.domain;

public enum Climate {
    POLAR("cold summer, cold winter"),
    TEMPERATE("warm summer, cold winter"),
    ARID("hot summer, warm winter"),
    TROPICAL("hot summer, hot winter");

    private String description;

    public String getDescription() {
        return description;
    }

    Climate(String description) {
        this.description = description;

    }
}
