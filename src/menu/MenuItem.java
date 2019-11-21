package menu;

public class MenuItem {
    private String name;
    private int category;
    private int spiciness;
    private String price;

    public MenuItem() {
        name = "";
        category = -1;
        spiciness = -1;
        price = "";
    }

    public MenuItem(String name, int category, int spiciness, String price) {
        this.name = name;
        this.category = category;
        this.spiciness = spiciness;
        this.price = price;
    }

    public MenuItem(MenuItem item) {
        name = item.getName();
        category = item.getCategory();
        spiciness = item.getSpiciness();
        price = item.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSpiciness() {
        return spiciness;
    }

    public void setSpiciness(int spiciness) {
        this.spiciness = spiciness;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return (!this.equals(new MenuItem()) ? name + " $" + price : "");
    }

    public boolean equals(MenuItem item) {
        return item.name.equals(name) && item.price.equals(price) && item.category == category
                && item.spiciness == spiciness;
    }
}
