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