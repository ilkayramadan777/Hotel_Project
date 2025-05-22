package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;

/**
 * Представлява резервация за хотелска стая.
 * Съдържа информация за период, гости, бележка и дали е недостъпност.
 */
public class Reservation {

    private final int roomNumber;
    private final LocalDate from;
    private final LocalDate to;
    private final String note;
    private final int guests;
    private final boolean isUnavailable;

    /**
     * Създава нова резервация или маркира стая като недостъпна.
     *
     * @param roomNumber    номер на стаята
     * @param from          начална дата
     * @param to            крайна дата
     * @param note          бележка
     * @param guests        брой гости
     * @param isUnavailable дали стаята е временно недостъпна
     */
    public Reservation(int roomNumber, LocalDate from, LocalDate to, String note, int guests, boolean isUnavailable) {
        this.roomNumber = roomNumber;
        this.from = from;
        this.to = to;
        this.note = note;
        this.guests = guests;
        this.isUnavailable = isUnavailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public String getNote() {
        return note;
    }

    public int getGuests() {
        return guests;
    }

    public boolean isUnavailable() {
        return isUnavailable;
    }

    /**
     * Връща текстово описание на резервацията.
     *
     * @return низ с основна информация
     */
    @Override
    public String toString() {
        return (isUnavailable ? "[Недостъпна]" : "[Резервация]") +
                " Стая " + roomNumber + ", от: " + from + ", до: " + to +
                ", Гости: " + guests + ", Бележка: " + note;
    }
}
