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
            System.out.println("Стая " + roomNumber + " е успешно резервирана.");
        } else {
            System.out.println("Стая " + roomNumber + " не е свободна за посочения период.");
        }
    }


    public void checkout(int roomNumber) {
        boolean removed = reservations.removeIf(
                reservation -> reservation.getRoomNumber() == roomNumber && !reservation.isUnavailable()
        );
        if (removed) {
            System.out.println("Стая " + roomNumber + " беше успешно освободена.");
        } else {
            System.out.println("Стая " + roomNumber + " не е заета.");
        }
    }


    public void availability(LocalDate date) {
        List<Integer> occupiedRooms = reservations.stream()
                .filter(res -> DateUtils.overlaps(date, date, res.getFrom(), res.getTo()))
                .map(Reservation::getRoomNumber)
                .collect(Collectors.toList());

        for (Room room : rooms) {
            if (!occupiedRooms.contains(room.getNumber())) {
                System.out.println(room);
            }
        }
    }


    public void report(LocalDate from, LocalDate to) {
        System.out.println("Справка за периода от " + from + " до " + to + ":");
        for (Room room : rooms) {
            int usedDays = 0;

            for (Reservation res : reservations) {
                if (res.getRoomNumber() == room.getNumber() && !res.isUnavailable()) {
                    LocalDate overlapStart = from.isAfter(res.getFrom()) ? from : res.getFrom();
                    LocalDate overlapEnd = to.isBefore(res.getTo()) ? to : res.getTo();

                    if (!overlapStart.isAfter(overlapEnd)) {
                        usedDays += overlapEnd.toEpochDay() - overlapStart.toEpochDay() + 1;
                    }
                }
            }

            if (usedDays > 0) {
                System.out.println("Стая " + room.getNumber() + ": " + usedDays + " дни.");
            }
        }
    }


    public void unavailable(int roomNumber, LocalDate from, LocalDate to, String note) {
        reservations.add(new Reservation(roomNumber, from, to, note, 0, true));
        System.out.println("Стая " + roomNumber + " е маркирана като недостъпна.");
    }


    public Optional<Room> find(int beds, LocalDate from, LocalDate to) {
        return rooms.stream()
                .filter(room -> room.getBeds() >= beds && isRoomAvailable(room.getNumber(), from, to))
                .sorted((r1, r2) -> Integer.compare(r1.getBeds(), r2.getBeds()))
                .findFirst();
    }


    public Optional<Room> findUrgent(int beds, LocalDate from, LocalDate to) {
        Optional<Room> directMatch = find(beds, from, to);
        if (directMatch.isPresent()) return directMatch;

        for (Room candidate : rooms) {
            if (candidate.getBeds() < beds) continue;

            List<Reservation> conflicting = reservations.stream()
                    .filter(res -> res.getRoomNumber() == candidate.getNumber())
                    .filter(res -> !res.isUnavailable() && DateUtils.overlaps(from, to, res.getFrom(), res.getTo()))
                    .toList();

            if (conflicting.size() > 2) continue;

            boolean allMoved = true;

            for (Reservation res : conflicting) {
                Optional<Room> alternative = rooms.stream()
                        .filter(alt -> alt.getNumber() != res.getRoomNumber() && alt.getBeds() >= res.getGuests())
                        .filter(alt -> isRoomAvailable(alt.getNumber(), res.getFrom(), res.getTo()))
                        .findFirst();

                if (alternative.isPresent()) {
                    reservations.add(new Reservation(
                            alternative.get().getNumber(),
                            res.getFrom(), res.getTo(),
                            res.getNote(),
                            res.getGuests(),
                            false
                    ));
                } else {
                    allMoved = false;
                    break;
                }
            }

            if (allMoved) {
                reservations.removeAll(conflicting);
                return Optional.of(candidate);
            }
        }

        return Optional.empty();
    }


    private boolean isRoomAvailable(int roomNumber, LocalDate from, LocalDate to) {
        return reservations.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .noneMatch(r -> DateUtils.overlaps(from, to, r.getFrom(), r.getTo()));
    }
}
