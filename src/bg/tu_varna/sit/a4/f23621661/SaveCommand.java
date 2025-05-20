package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

public class SaveCommand implements Command {
    private Hotel hotel;
    private FileManager fileManager;

    public SaveCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            fileManager.save(hotel);
        } catch (IOException e) {
            System.out.println("Грешка при запазване на файла: " + e.getMessage());
        }
    }
}

