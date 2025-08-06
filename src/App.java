import java.util.*;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        try {
            System.out.println("Введите покупателей (формат: Имя = Деньги; ...):");
            String inputPeople = scanner.nextLine();
            for (String entry : inputPeople.split(";")) {
                if (entry.isBlank()) continue;

                String[] parts = entry.split("=");
                if (parts.length != 2) {
                    System.err.println("Ошибка: неверный формат ввода покупателя");
                    continue;
                }

                String name = parts[0].trim();
                int money = Integer.parseInt(parts[1].trim());

                try {
                    people.add(new Person(name, money));
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }

            System.out.println("Введите продукты (формат: Название = Цена; ...):");
            String inputProducts = scanner.nextLine();
            for (String entry : inputProducts.split(";")) {
                if (entry.isBlank()) continue;

                String[] parts = entry.split("=");
                if (parts.length != 2) {
                    System.err.println("Ошибка: неверный формат ввода продукта");
                    continue;
                }

                String name = parts[0].trim();
                int price = Integer.parseInt(parts[1].trim());

                try {
                    products.add(new Product(name, price));
                } catch (IllegalArgumentException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }

            System.out.println("Введите покупки (формат: Имя - Название продукта), для завершения введите END:");
            while (true) {
                String purchaseLine = scanner.nextLine();
                if (purchaseLine.equalsIgnoreCase("END")) break;

                String[] parts = purchaseLine.split(" - ");
                if (parts.length != 2) {
                    System.err.println("Ошибка: введите в формате 'Имя - Продукт'");
                    continue;
                }

                String personName = parts[0].trim();
                String productName = parts[1].trim();

                Person person = findPersonByName(people, personName);
                Product product = findProductByName(products, productName);

                if (person == null) {
                    System.err.println("Покупатель не найден: " + personName);
                    continue;
                }

                if (product == null) {
                    System.err.println("Продукт не найден: " + productName);
                    continue;
                }

                if (person.getMoney() >= product.getPrice()) {
                    person.setMoney(person.getMoney() - product.getPrice());
                    person.addProduct(product);
                    System.out.printf("%s купил %s%n", person.getName(), product.getName());
                } else {
                    System.out.printf("%s не может позволить себе %s%n", person.getName(), product.getName());
                }
            }

            System.out.println();
            for (Person person : people) {
                List<Product> bucket = person.getProducts();
                if (bucket.isEmpty()) {
                    System.out.printf("%s - Ничего не куплено%n", person.getName());
                } else {
                    StringBuilder result = new StringBuilder();
                    result.append(person.getName()).append(" - ");
                    for (int i = 0; i < bucket.size(); i++) {
                        result.append(bucket.get(i).getName());
                        if (i < bucket.size() - 1) {
                            result.append(", ");
                        }
                    }
                    System.out.println(result);
                }
            }

        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Person findPersonByName(List<Person> people, String name) {
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    private static Product findProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
