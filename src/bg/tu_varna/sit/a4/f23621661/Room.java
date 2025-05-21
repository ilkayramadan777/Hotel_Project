package bg.tu_varna.sit.a4.f23621661;

/**
 * Клас, представящ хотелска стая с номер и брой легла.
 */
/**
 * Представя хотелска стая с номер и брой легла.
 */
public class Room {
    private int number;
    private int beds;

    /**
     * Създава нова стая.
     * @param number номер на стаята
     * @param beds брой легла
     */
    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
    }

    /**
     * Връща номера на стаята.
     * @return номер на стаята
     */
    public int getNumber() {
        return number;
    }

    /**
     * Връща броя на леглата в стаята.
     * @return брой легла
     */
    public int getBeds() {
        return beds;
    }

    @Override
    public String toString() {
        return "Стая " + number + " (" + beds + " легла)";
    }
}

