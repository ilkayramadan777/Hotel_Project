package bg.tu_varna.sit.a4.f23621661;

import java.io.*;
import java.time.LocalDate;

/**
 * Отговаря за зареждане, запис и затваряне на хотелски данни от/в текстов файл.
 * Използва се за управление на стаи и резервации.
 */
public class FileManager {

    /**
     * Името на текущо заредения файл.
     */
    private String currentFile;

    /**
     * Зарежда данни от файл и инициализира стаи и резервации.
     * Ако файлът не съществува, създава нов празен файл.
     *
     * @param hotel    хотелският обект, който ще бъде инициализиран
     * @param filename име на файла за зареждане или създаване
     * @throws IOException при проблем с четенето или създаването
     */
    public void open(Hotel hotel, String filename) throws IOException {
        hotel.getRooms().clear();
        hotel.getReservations().clear();

        File file = new File(filename);

        if (!file.exists()) {
            if (file.createNewFile()) {
                currentFile = filename;
                System.out.println("Създаден нов празен файл: '" + filename + "'");
                return;
            } else {
                throw new IOException("Неуспешно създаване на файл: '" + filename + "'");
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
     * Записва данните във вече отворения файл.
     *
     * @param hotel хотелът, чиито данни ще се запишат
     * @throws IOException ако възникне грешка при запис
     */
    public void save(Hotel hotel) throws IOException {
        if (currentFile == null)
            throw new IllegalStateException("Няма отворен файл. Първо използвайте saveas <файл>.");
        saveAs(hotel, currentFile);
    }

    /**
     * Записва данните в нов файл.
     *
     * @param hotel    хотелът, чиито данни ще се запишат
     * @param filename име на новия файл
     * @throws IOException при проблем с писането
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
     * Затваря текущите данни и нулира информацията за файла.
     *
     * @param hotel хотелът, чийто данни ще се изчистят
     */
    public void close(Hotel hotel) {
        hotel.getRooms().clear();
        hotel.getReservations().clear();
        currentFile = null;
        System.out.println("Данните за хотела са затворени.");
    }

    /**
     * Проверява дали има отворен файл.
     *
     * @return true ако има активен файл, иначе false
     */
    public boolean isFileOpened() {
        return currentFile != null;
    }
}
