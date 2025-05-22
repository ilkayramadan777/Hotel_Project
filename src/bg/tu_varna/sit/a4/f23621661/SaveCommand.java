package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

/**
 * Команда за запазване на текущите хотелски данни
 * във вече отворения файл.
 */
public class SaveCommand implements Command {

    private final Hotel hotel;
    private final FileManager fileManager;

    /**
     * Създава нова команда за стандартно запазване.
     *
     * @param hotel       хотелският обект
     * @param fileManager мениджър за работа с файлове
     */
    public SaveCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява запис на данните във вече отворения файл.
     * При липса на отворен файл се извежда съобщение за грешка.
     *
     * @param args аргументи (не се използват)
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
