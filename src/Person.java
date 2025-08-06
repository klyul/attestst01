import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int money;
    private final List<Product> productBucket;

    public Person(String name, int money) {
        setName(name);
        setMoney(money);
        this.productBucket = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(productBucket);
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "Имя не может быть null");
        String trimmed = name.trim();
        if (trimmed.length() < 3) {
            throw new IllegalArgumentException("Имя должно быть минимум 3 символа");
        }
        this.name = trimmed;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Продукт не может быть null");
        productBucket.add(product);
    }

    @Override
    public String toString() {
        return String.format("%s (Деньги: %d, Продукты: %s)",
                name, money, productBucket);
    }
}