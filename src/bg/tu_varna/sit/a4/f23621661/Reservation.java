package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Класът {@code Reservation} представлява резервация за хотелска стая.
 * Съдържа информация за номер на стая, период на престой, бележка, брой гости
 * и дали резервацията е за недостъпност (например при ремонт).
 */
public class Reservation {

    private final int roomNumber;
    private final LocalDate from;
    private final LocalDate to;
    private final String note;
    private final int guests;
    private final boolean isUnavailable;

    /**
     * Конструктор за създаване на нова резервация.
     *
     * @param roomNumber     номер на стаята
     * @param from           начална дата на престоя
     * @param to             крайна дата на престоя
     * @param note           бележка към резервацията
     * @param guests         брой гости
     * @param isUnavailable  дали стаята е недостъпна (true – недостъпна; false – реална резервация)
     */
    public Reservation(int roomNumber, LocalDate from, LocalDate to, String note, int guests, boolean isUnavailable) {
        this.roomNumber = roomNumber;
        this.from = from;
        this.to = to;
        this.note = note;
        this.guests = guests;
        this.isUnavailable = isUnavailable;
    }

    /**
     * Връща номера на стаята.
     *
     * @return номер на стаята
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Връща началната дата на резервацията.
     *
     * @return начална дата
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Връща крайната дата на резервацията.
     *
     * @return крайна дата
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Връща бележката към резервацията.
     *
     * @return бележка
     */
    public String getNote() {
        return note;
    }

    /**
     * Връща броя на гостите.
     *
     * @return брой гости
     */
    public int getGuests() {
        return guests;
    }

    /**
     * Проверява дали стаята е маркирана като временно недостъпна.
     *
     * @return {@code true}, ако е недостъпна; {@code false} ако е стандартна резервация
     */
    public boolean isUnavailable() {
        return isUnavailable;
    }

    /**
     * Връща текстово представяне на резервацията.
     *
     * @return низ с основна информация за резервацията
     */
    @Override
    public String toString() {
        return (isUnavailable ? "[Недостъпна]" : "[Резервация]") +
                " Стая " + roomNumber + ", от: " + from + ", до: " + to +
                ", Гости: " + guests + ", Бележка: " + note;
    }
}
