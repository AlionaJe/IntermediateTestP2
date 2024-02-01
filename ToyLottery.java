import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Toy {
    int id;
    String name;
    int frequency; // Частота выпадения игрушки

    // Конструктор класса Toy
    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }
}

public class ToyLottery {
    // Создаем коллекцию для хранения всех игрушек в магазине
    private static final List<Toy> toysInStore = new ArrayList<>();

    // Создаем приоритетную очередь для хранения всех игрушек в магазине
    private static final PriorityQueue<Toy> toyQueue = new PriorityQueue<>(Comparator.comparingInt(Toy::hashCode));

    // Создаем коллекцию для хранения выигранных призов
    private static final List<String> prize = new ArrayList<>();

    // Создаем генератор случайных чисел
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Инициализируем магазин изначальными товарами
        Toy[] toys = {
                new Toy(1, "конструктор", 20),
                new Toy(2, "робот", 20),
                new Toy(3, "кукла", 60)
        };
        