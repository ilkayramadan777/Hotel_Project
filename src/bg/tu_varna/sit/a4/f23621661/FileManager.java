package bg.tu_varna.sit.a4.f23621661;

import java.io.*;
import java.time.LocalDate;

public class FileManager {
    private String currentFile;

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

    public void save(Hotel hotel) throws IOException {
        if (currentFile == null) throw new IllegalStateException("Няма отворен файл. Първо използвайте saveas <файл>.");
        saveAs(hotel, currentFile);
    }

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

    public void close(Hotel hotel) {
        hotel.getRooms().clear();
        hotel.getReservations().clear();
        currentFile = null;
        System.out.println("Данните за хотела са затворени.");
    }
}