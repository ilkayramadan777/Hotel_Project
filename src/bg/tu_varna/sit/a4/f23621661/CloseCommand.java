package bg.tu_varna.sit.a4.f23621661;

public class CloseCommand implements Command {
    private Hotel hotel;
    private FileManager fileManager;

    public CloseCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        fileManager.close(hotel);
    }
}
