package bg.tu_varna.sit.a4.f23621661;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void checkin(int roomNumber, LocalDate from, LocalDate to, String note, int guests) {
        if (isRoomAvailable(roomNumber, from, to)) {
            reservations.add(new Reservation(roomNumber, from, to, note, guests, false));
            System.out.println("Room " + roomNumber + " checked in successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not available for the selected period.");
        }
    }

    public void checkout(int roomNumber) {
        boolean removed = reservations.removeIf(r -> r.getRoomNumber() == roomNumber && !r.isUnavailable());
        if (removed) {
            System.out.println("Room " + roomNumber + " successfully checked out.");
        } else {
            System.out.println("Room " + roomNumber + " is not currently occupied.");
        }
    }

    public void availability(LocalDate date) {
        List<Integer> occupied = reservations.stream()
                .filter(r -> !r.isUnavailable() && !date.isBefore(r.getFrom()) && !date.isAfter(r.getTo()))
                .map(Reservation::getRoomNumber)
                .collect(Collectors.toList());

        rooms.stream()
                .filter(room -> !occupied.contains(room.getNumber()))
                .forEach(System.out::println);
    }

    public void report(LocalDate from, LocalDate to) {
        System.out.println("Usage report from " + from + " to " + to + ":");
        for (Room room : rooms) {
            int daysUsed = 0;
            for (Reservation r : reservations) {
                if (r.getRoomNumber() == room.getNumber() && !r.isUnavailable()) {
                    LocalDate overlapStart = from.isAfter(r.getFrom()) ? from : r.getFrom();
                    LocalDate overlapEnd = to.isBefore(r.getTo()) ? to : r.getTo();
                    if (!overlapStart.isAfter(overlapEnd)) {
                        daysUsed += overlapEnd.toEpochDay() - overlapStart.toEpochDay() + 1;
                    }
                }
            }
            if (daysUsed > 0) {
                System.out.println("Room " + room.getNumber() + ": " + daysUsed + " days used");
            }
        }
    }

    public void unavailable(int roomNumber, LocalDate from, LocalDate to, String note) {
        reservations.add(new Reservation(roomNumber, from, to, note, 0, true));
        System.out.println("Room " + roomNumber + " marked as unavailable.");
    }

    public Optional<Room> find(int beds, LocalDate from, LocalDate to) {
        return rooms.stream()
                .filter(r -> r.getBeds() >= beds && isRoomAvailable(r.getNumber(), from, to))
                .sorted((r1, r2) -> Integer.compare(r1.getBeds(), r2.getBeds()))
                .findFirst();
    }

    private boolean isRoomAvailable(int roomNumber, LocalDate from, LocalDate to) {
        return reservations.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .noneMatch(r -> DateUtils.overlaps(from, to, r.getFrom(), r.getTo()));
    }
}
