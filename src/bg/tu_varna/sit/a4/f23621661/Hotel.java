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
            System.out.println("Стая " + roomNumber + " е резервирана успешно.");
        } else {
            System.out.println("Стая " + roomNumber + " не може да бъде резервирана за тези дати. Моля изберете други дати или стая!");
        }
    }

    public void checkout(int roomNumber) {
        boolean removed = reservations.removeIf(r -> r.getRoomNumber() == roomNumber && !r.isUnavailable());
        if (removed) {
            System.out.println("Стая " + roomNumber + " е освободена");
        } else {
            System.out.println("Стая " + roomNumber + " е свободна");
        }
    }

    public void availability(LocalDate date) {
        List<Integer> unavailableRooms = reservations.stream()
                .filter(r -> DateUtils.overlaps(date, date, r.getFrom(), r.getTo()))
                .map(Reservation::getRoomNumber)
                .collect(Collectors.toList());

        for (Room room : rooms) {
            if (!unavailableRooms.contains(room.getNumber())) {
                System.out.println(room);
            }
        }
    }


    public void report(LocalDate from, LocalDate to) {
        System.out.println("Справка за периода от " + from + " до " + to + ":");
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
                System.out.println("Стая" + room.getNumber() + ": " + daysUsed + " дена е използвана");
            }
        }
    }

    public void unavailable(int roomNumber, LocalDate from, LocalDate to, String note) {
        reservations.add(new Reservation(roomNumber, from, to, note, 0, true));
        System.out.println("Стая " + roomNumber + "е временно недостъпна");
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
    public Optional<Room> findUrgent(int beds, LocalDate from, LocalDate to) {

        Optional<Room> direct = find(beds, from, to);
        if (direct.isPresent()) return direct;


        for (Room targetRoom : rooms) {
            if (targetRoom.getBeds() < beds) continue;

            List<Reservation> conflicts = reservations.stream()
                    .filter(r -> r.getRoomNumber() == targetRoom.getNumber())
                    .filter(r -> !r.isUnavailable() && DateUtils.overlaps(from, to, r.getFrom(), r.getTo()))
                    .toList();

            if (conflicts.size() > 2) continue;

            boolean canMoveAll = true;

            for (Reservation r : conflicts) {
                boolean moved = rooms.stream()
                        .filter(alt -> alt.getNumber() != r.getRoomNumber() && alt.getBeds() >= r.getGuests())
                        .filter(alt -> isRoomAvailable(alt.getNumber(), r.getFrom(), r.getTo()))
                        .findFirst()
                        .map(newRoom -> {
                            reservations.add(new Reservation(newRoom.getNumber(), r.getFrom(), r.getTo(), r.getNote(), r.getGuests(), false));
                            return true;
                        })
                        .orElse(false);

                if (!moved) {
                    canMoveAll = false;
                    break;
                }
            }

            if (canMoveAll) {
                reservations.removeAll(conflicts);
                return Optional.of(targetRoom);
            }
        }

        return Optional.empty();
    }

}