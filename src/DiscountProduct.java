import java.time.LocalDateTime;

public class DiscountProduct extends Product {
    private final double discountPercentage;
    private final LocalDateTime expirationDate;

    public DiscountProduct(String name, int originalPrice, double discountPercentage, LocalDateTime expirationDate) {
        super(name, originalPrice);
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    //Рассчитываем итоговую цену товара с учётом скидки
    private int calculateDiscountedPrice(int originalPrice, double discountPercentage) {
        return (int) (originalPrice * (1 - discountPercentage / 100.0));
    }

    // Проверка, действует ли скидка на текущий момент
    public boolean isDiscountActive(LocalDateTime now) {
        return now.isBefore(expirationDate);
    }

    //Получаем актуальную цену товара в зависимости от статуса скидки
    @Override
    public int getPrice() {
        LocalDateTime now = LocalDateTime.now();
        if (isDiscountActive(now)) {
            return super.getPrice();
        } else {
            return calculateOriginalPrice(super.getPrice());
        }
    }

    // Расчёт оригинальной полной цены товара без учёта скидки
    private int calculateOriginalPrice(int discountedPrice) {
        return (int)(discountedPrice / (1 - discountPercentage / 100.0));
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return String.format("%s (Цена: %d руб., Скидка: %.2f%%, Срок действия: %s)",
                getName(),
                getPrice(),
                discountPercentage,
                expirationDate.toString()
        );
    }
}