package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Клас {@code Hotel} представлява модел на хотел със списък от стаи и резервации.
 * Осигурява функционалност за настаняване, освобождаване, справки и търсене на стаи.
 */
public class Hotel {
    private final List<Room> rooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    /**
     * Добавя нова стая към хотела.
     *
     * @param room обект от тип {@code Room}
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Връща списък с всички стаи в хотела.
     *
     * @return списък със стаи
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Връща списък с всички резервации в хотела.
     *
     * @return списък с резервации
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Настанява гости в определена стая за даден период.
     *
     * @param roomNumber номер на стаята
     * @param from       начална дата на престоя
     * @param to         крайна дата на престоя
     * @param note       бележка за престоя
     * @param guests     брой гости
     */
    public void checkin(int roomNumber, LocalDate from, LocalDate to, String note, int guests) {
        if (isRoomAvailable(roomNumber, from, to)) {
            reservations.add(new Reservation(roomNumber, from, to, note, guests, false));
            System.out.println("Стая " + roomNumber + " е резервирана успешно.");
        } else {
            System.out.println("Стая " + roomNumber + " не може да бъде резервирана за тези дати. Моля, изберете други дати или стая!");
        }
    }

    /**
     * Освобождава стая с дадения номер.
     *
     * @param roomNumber номер на стаята
     */
    public void checkout(int roomNumber) {
        boolean removed = reservations.removeIf(r -> r.getRoomNumber() == roomNumber && !r.isUnavailable());
        if (removed) {
            System.out.println("Стая " + roomNumber + " е освободена.");
        } else {
            System.out.println("Стая " + roomNumber + " е свободна.");
        }
    }

    /**
     * Извежда списък със свободните стаи за дадена дата.
     *
     * @param date дата за проверка
     */
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

    /**
     * Извежда справка за използване на стаите в даден период.
     *
     * @param from начална дата
     * @param to   крайна дата
     */
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
                System.out.println("Стая " + room.getNumber() + ": " + daysUsed + " дена е използвана");
            }
        }
    }

    /**
     * Маркира стая като временно недостъпна.
     *
     * @param roomNumber номер на стаята
     * @param from       начална дата
     * @param to         крайна дата
     * @param note       бележка
     */
    public void unavailable(int roomNumber, LocalDate from, LocalDate to, String note) {
        reservations.add(new Reservation(roomNumber, from, to, note, 0, true));
        System.out.println("Стая " + roomNumber + " е временно недостъпна.");
    }

    /**
     * Намира свободна стая с поне определен брой легла.
     *
     * @param beds минимален брой легла
     * @param from начална дата
     * @param to   крайна дата
     * @return {@code Optional<Room>} със свободна стая, ако има налична
     */
    public Optional<Room> find(int beds, LocalDate from, LocalDate to) {
        return rooms.stream()
                .filter(r -> r.getBeds() >= beds && isRoomAvailable(r.getNumber(), from, to))
                .sorted((r1, r2) -> Integer.compare(r1.getBeds(), r2.getBeds()))
                .findFirst();
    }

    /**
     * Проверява дали дадена стая е свободна за конкретен период.
     *
     * @param roomNumber номер на стаята
     * @param from       начална дата
     * @param to         крайна дата
     * @return {@code true}, ако стаята е свободна; {@code false} иначе
     */
    private boolean isRoomAvailable(int roomNumber, LocalDate from, LocalDate to) {
        return reservations.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .noneMatch(r -> DateUtils.overlaps(from, to, r.getFrom(), r.getTo()));
    }

    /**
     * Извършва спешно намиране на стая чрез пренареждане на до две други резервации.
     *
     * @param beds минимален брой легла
     * @param from начална дата
     * @param to   крайна дата
     * @return {@code Optional<Room>} със стая, ако е възможно пренареждане
     */
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

    /**
     * Проверява дали съществува стая с дадения номер.
     *
     * @param roomNumber номер на стаята
     * @return {@code true}, ако стаята съществува
     */
    public boolean roomExists(int roomNumber) {
        return rooms.stream().anyMatch(r -> r.getNumber() == roomNumber);
    }

    /**
     * Връща броя на леглата в дадена стая.
     *
     * @param roomNumber номер на стаята
     * @return брой легла или 0, ако стаята не съществува
     */
    public int getRoomBeds(int roomNumber) {
        return rooms.stream()
                .filter(r -> r.getNumber() == roomNumber)
                .map(Room::getBeds)
                .findFirst()
                .orElse(0);
    }
}
