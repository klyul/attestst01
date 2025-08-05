public class App {
    public static void main(String[] args) {
        try {
            Person person = new Person("Анна", 500);
            person.addProduct(new Product("Яблоко", 50));
            person.addProduct(new Product("Молоко", 80));

            System.out.println(person);

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}