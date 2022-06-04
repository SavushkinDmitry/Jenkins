package steps;

public enum ProductCategory {
    оргтехника("Оргтехника и расходники", "#category");

    String name;
    String id;

    ProductCategory(String name, String id) {
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
