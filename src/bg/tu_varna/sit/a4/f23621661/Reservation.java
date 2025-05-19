package bg.tu_varna.sit.a4.f23621661;


import java.time.LocalDate;

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

    @Override
    public String toString() {
        return (isUnavailable ? "[Недостъпна]" : "[Резервация]") +
                " Стая " + roomNumber + ", от: " + from + ", до: " + to +
                ", Гости: " + guests + ", Бележка: " + note;
    }
}
