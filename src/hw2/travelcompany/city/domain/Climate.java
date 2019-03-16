package hw2.travelcompany.city.domain;

public enum Climate {
    POLAR("cold winter, cold summer"),
    CONTINENTAL("cold winter, warm summer"),
    TEMPERATE("hot summer, warm winter"),
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
