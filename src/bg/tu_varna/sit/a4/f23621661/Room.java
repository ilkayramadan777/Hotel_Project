package bg.tu_varna.sit.a4.f23621661;

/**
 * Класът {@code Room} представлява хотелска стая с уникален номер и брой легла.
 * Използва се за описание на капацитета на всяка стая в хотела.
 */
public class Room {

    private final int number;
    private final int beds;

    /**
     * Конструктор за създаване на нова стая.
     *
     * @param number номер на стаята
     * @param beds   брой легла в стаята
     */
    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
    }

    /**
     * Връща номера на стаята.
     *
     * @return номер на стаята
     */
    public int getNumber() {
        return number;
    }

    /**
     * Връща броя на леглата в стаята.
     *
     * @return брой легла
     */
    public int getBeds() {
        return beds;
    }

    /**
     * Връща текстово представяне на стаята.
     *
     * @return низ с формат: "Стая X (Y легла)"
     */
    @Override
    public String toString() {
        return "Стая " + number + " (" + beds + " легла)";
    }
}
