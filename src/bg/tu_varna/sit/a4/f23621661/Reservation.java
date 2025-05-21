package bg.tu_varna.sit.a4.f23621661;


import java.time.LocalDate;

/**
 * Клас, представящ резервация в хотел.
 * Съдържа информация за стая, период, бележка, брой гости и дали стаята е недостъпна.
 */
/**
 * Представя резервация за хотелска стая.
 * Съдържа период, брой гости, бележка и дали стаята е недостъпна.
 */
public class Reservation {
    private int roomNumber;
    private LocalDate from;
    private LocalDate to;
    private String note;
    private int guests;
    private boolean isUnavailable;

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
     * @return номер на стаята
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Връща началната дата на резервацията.
     * @return начална дата
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Връща крайната дата на резервацията.
     * @return крайна дата
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Връща бележката към резервацията.
     * @return бележка
     */
    public String getNote() {
        return note;
    }

    /**
     * Връща броя на гостите.
     * @return брой гости
     */
    public int getGuests() {
        return guests;
    }

    /**
     * Проверява дали стаята е недостъпна.
     * @return true ако е недостъпна
     */
    public boolean isUnavailable() {
        return isUnavailable;
    }

    @Override
    public String toString() {
        return (isUnavailable ? "[Недостъпна]" : "[Резервация]") +
                " Стая " + roomNumber + ", от: " + from + ", до: " + to +
                ", Гости: " + guests + ", Бележка: " + note;
    }
}
