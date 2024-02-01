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

        // Заполняем коллекцию игрушками
        toysInStore.addAll(Arrays.asList(toys));

        // Заполняем приоритетную очередь игрушками
        toyQueue.addAll(Arrays.asList(toys));

        // Выполняем розыгрыш и записываем результат в файл
        for (int i = 0; i < 10; i++) {
            getPrize();
        }
        writeToFile();
    }

    // Метод для проведения розыгрыша
    public static void getPrize() {
        // Проверяем, есть ли игрушки в магазине
        if (toysInStore.isEmpty()) {
            System.out.println("Магазин пуст, добавьте новые игрушки.");
            return;
        }

        // Суммируем частоты всех игрушек в магазине
        int totalFrequency = toysInStore.stream().mapToInt(toy -> toy.frequency).sum();

        // Генерируем случайное число в диапазоне от 0 до totalFrequency - 1
        int randomValue = random.nextInt(totalFrequency);

        // Определяем, какая игрушка выпала в результате розыгрыша
        Toy selectedToy = null;
        int currentFrequency = 0;
        for (Toy toy : toysInStore) {
            currentFrequency += toy.frequency;
            if (randomValue < currentFrequency) {
                selectedToy = toy;
                break;
            }
        }

        // Уменьшаем количество выбранной игрушки в магазине
        if (selectedToy != null) {
            selectedToy.frequency--;
            // Добавляем название выбранной игрушки в выигранные призы
            prize.add(selectedToy.name);
            System.out.println("Поздравляем! Вы выиграли " + selectedToy.name);
        } else {
            System.out.println("Не удалось определить выигрышную игрушку.");
        }
    }

    // Метод для записи результатов розыгрыша в файл
    public static void writeToFile() {
        try (FileWriter writer = new FileWriter("results.txt")) {
            for (String result : prize) {
                writer.write(result + "\n");
            }
            System.out.println("Результаты розыгрыша записаны в файл results.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
