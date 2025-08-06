import java.util.Objects;

public class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = validateName(name);
        this.price = validatePrice(price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    private String validateName(String name) {
        Objects.requireNonNull(name, "Название продукта не может быть null");
        String trimmed = name.trim();
        if (trimmed.length() < 2) {
            throw new IllegalArgumentException("Название продукта должно быть минимум 2 символа");
        }
        return trimmed;
    }

    private int validatePrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (Цена: %d)", name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}