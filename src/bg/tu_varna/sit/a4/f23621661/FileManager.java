package bg.tu_varna.sit.a4.f23621661;

import java.io.*;
import java.time.LocalDate;

/**
 * Клас {@code FileManager} отговаря за зареждане, запазване и затваряне на данните на хотел
 * от/във текстов файл. Използва се за сериализация на стаи и резервации.
 */
public class FileManager {

    /**
     * Името на текущо заредения файл.
     */
    private String currentFile;

    /**
     * Зарежда данни от подаден файл и инициализира хотелските стаи и резервации.
     *
     * @param hotel    хотелският обект, в който се зареждат данните
     * @param filename името на файла за четене
     * @throws IOException ако файлът не може да бъде прочетен
     */
    public void open(Hotel hotel, String filename) throws IOException {
        hotel.getRooms().clear();
        hotel.getReservations().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean inRooms = false;
            boolean inReservations = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equalsIgnoreCase("ROOMS")) {
                    inRooms = true;
                    inReservations = false;
                    continue;
                } else if (line.equalsIgnoreCase("RESERVATIONS")) {
                    inRooms = false;
                    inReservations = true;
                    continue;
                }

                if (inRooms && !line.isEmpty()) {
                    String[] parts = line.split(" ");
                    int roomNumber = Integer.parseInt(parts[0]);
                    int beds = Integer.parseInt(parts[1]);
                    hotel.addRoom(new Room(roomNumber, beds));
                } else if (inReservations && !line.isEmpty()) {
                    String[] parts = line.split(" ");
                    int roomNumber = Integer.parseInt(parts[0]);
                    LocalDate from = DateUtils.parse(parts[1]);
                    LocalDate to = DateUtils.parse(parts[2]);
                    String note = parts[3];
                    int guests = Integer.parseInt(parts[4]);
                    boolean isUnavailable = Boolean.parseBoolean(parts[5]);

                    hotel.getReservations().add(new Reservation(roomNumber, from, to, note, guests, isUnavailable));
                }
            }

            currentFile = filename;
            System.out.println("Файл '" + filename + "' е отворен успешно.");
        }
    }

    /**
     * Запазва хотелските данни във вече отворения файл.
     *
     * @param hotel обектът {@code Hotel}, чиито данни се записват
     * @throws IOException               при грешка при запис
     * @throws IllegalStateException    ако няма отворен файл
     */
    public void save(Hotel hotel) throws IOException {
        if (currentFile == null)
            throw new IllegalStateException("Няма отворен файл. Първо използвайте saveas <файл>.");
        saveAs(hotel, currentFile);
    }

    /**
     * Запазва хотелските данни в нов файл.
     *
     * @param hotel    обектът {@code Hotel}, чиито данни се записват
     * @param filename име на файла за запис
     * @throws IOException при грешка при запис
     */
    public void saveAs(Hotel hotel, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ROOMS\n");
            for (Room room : hotel.getRooms()) {
                writer.write(room.getNumber() + " " + room.getBeds() + "\n");
            }

            writer.write("\nRESERVATIONS\n");
            for (Reservation res : hotel.getReservations()) {
                writer.write(res.getRoomNumber() + " " +
                        DateUtils.format(res.getFrom()) + " " +
                        DateUtils.format(res.getTo()) + " " +
                        res.getNote() + " " +
                        res.getGuests() + " " +
                        res.isUnavailable() + "\n");
            }

            currentFile = filename;
            System.out.println("Файлът е запазен в '" + filename + "'");
        }
    }

    /**
     * Затваря текущо заредените данни и нулира текущия файл.
     *
     * @param hotel обектът {@code Hotel}, чиито данни се изчистват
     */
    public void close(Hotel hotel) {
        hotel.getRooms().clear();
        hotel.getReservations().clear();
        currentFile = null;
        System.out.println("Данните за хотела са затворени.");
    }
}
