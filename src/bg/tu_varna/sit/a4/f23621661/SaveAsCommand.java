package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

public class SaveAsCommand implements Command {
    private Hotel hotel;
    private FileManager fileManager;

    public SaveAsCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Въведете: saveas <файл>");
            return;
        }
        try {
            fileManager.saveAs(hotel, args[1]);
        } catch (IOException e) {
            System.out.println("Грешка при запазване с ново име: " + e.getMessage());
        }
    }
}


