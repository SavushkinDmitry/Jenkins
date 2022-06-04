package steps;

public enum PriceCategory {
    дороже("Дороже", "//div[contains(@class, 'index-content')]//select[contains(@class, 'select-select') and position()=1]");

    String name;
    String id;

    PriceCategory(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
