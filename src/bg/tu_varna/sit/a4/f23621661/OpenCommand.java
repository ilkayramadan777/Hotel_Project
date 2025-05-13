package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

public class OpenCommand implements Command {
    private Hotel hotel;
    private FileManager fileManager;

    public OpenCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: open <file>");
            return;
        }
        try {
            fileManager.open(hotel, args[1]);
        } catch (IOException e) {
            System.out.println("Error while opening file: " + e.getMessage());
        }
    }
}

