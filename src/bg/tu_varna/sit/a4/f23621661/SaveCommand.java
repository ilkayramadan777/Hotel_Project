package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

/**
 * Класът {@code SaveCommand} представлява команда за запазване на текущите
 * хотелски данни във вече отворения файл.
 * Част е от реализацията на шаблона Command.
 */
public class SaveCommand implements Command {

    /**
     * Хотелският обект, чиито данни ще се запазват.
     */
    private final Hotel hotel;

    /**
     * Мениджър за файлови операции.
     */
    private final FileManager fileManager;

    /**
     * Конструктор за създаване на команда {@code SaveCommand}.
     *
     * @param hotel       обект от тип {@code Hotel}, с текущите данни
     * @param fileManager обект от тип {@code FileManager}, който извършва запис във файл
     */
    public SaveCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява командата за запис във вече отворения файл.
     * Ако няма отворен файл, {@code FileManager} ще хвърли изключение.
     *
     * @param args масив от аргументи (не се използват за тази команда)
     */
    @Override
    public void execute(String[] args) {
        try {
            fileManager.save(hotel);
            System.out.println("Файлът е запазен успешно.");
        } catch (IOException e) {
            System.out.println("Грешка при запазване на файла: " + e.getMessage());
        }
    }
}
