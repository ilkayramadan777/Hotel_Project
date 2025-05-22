package bg.tu_varna.sit.a4.f23621661;

/**
 * Представлява хотелска стая с номер и брой легла.
 */
public class Room {

    private final int number;
    private final int beds;

    /**
     * Създава нова стая с посочен номер и капацитет.
     *
     * @param number номер на стаята
     * @param beds   брой легла
     */
    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
    }

    public int getNumber() {
        return number;
    }

    public int getBeds() {
        return beds;
    }

    /**
     * Връща текстово представяне на стаята.
     *
     * @return формат: "Стая X (Y легла)"
     */
    @Override
    public String toString() {
        return "Стая " + number + " (" + beds + " легла)";
    }
}
