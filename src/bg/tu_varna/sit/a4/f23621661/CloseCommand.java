package bg.tu_varna.sit.a4.f23621661;

/**
 * Класът {@code CloseCommand} представлява команда за затваряне на текущо заредения файл с хотелски данни.
 * Част е от реализацията на шаблона Command.
 */
public class CloseCommand implements Command {

    /**
     * Хотелът, с който се работи.
     */
    private Hotel hotel;

    /**
     * Мениджър за работа с файлове.
     */
    private FileManager fileManager;

    /**
     * Конструктор за създаване на нова команда {@code CloseCommand}.
     *
     * @param hotel       обект от тип {@code Hotel}, свързан с текущия файл
     * @param fileManager обект от тип {@code FileManager}, който управлява файловите операции
     */
    public CloseCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява командата за затваряне на файла, асоцииран с {@code hotel}.
     * Използва {@code FileManager} за затваряне и изписва потвърждение.
     *
     * @param args масив от аргументи (не се използват при тази команда)
     */
    @Override
    public void execute(String[] args) {
        fileManager.close(hotel);
        System.out.println("Файлът е затворен успешно.");
    }
}
