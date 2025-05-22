package bg.tu_varna.sit.a4.f23621661;

/**
 * Команда за затваряне на текущо заредения файл с хотелски данни.
 */
public class CloseCommand implements Command {

    /**
     * Хотелът, чийто данни ще се затворят.
     */
    private Hotel hotel;

    /**
     * Мениджър за работа с файлове.
     */
    private FileManager fileManager;

    /**
     * Създава нова команда за затваряне на файл.
     *
     * @param hotel       обектът на хотела, чийто данни се обработват
     * @param fileManager обект за управление на файлови операции
     */
    public CloseCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява операцията по затваряне на файла.
     *
     * @param args аргументи (не се използват)
     */
    @Override
    public void execute(String[] args) {
        fileManager.close(hotel);
        System.out.println("Файлът е затворен успешно.");
    }
}
