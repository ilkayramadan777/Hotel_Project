package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

/**
 * Класът {@code OpenCommand} представлява команда за отваряне на файл,
 * съдържащ информация за хотелски стаи и резервации.
 * Част е от реализацията на шаблона Command.
 */
public class OpenCommand implements Command {

    /**
     * Обект от тип {@code Hotel}, който ще се инициализира с данни от файла.
     */
    private final Hotel hotel;

    /**
     * Мениджър за файлови операции.
     */
    private final FileManager fileManager;

    /**
     * Конструктор за създаване на команда {@code OpenCommand}.
     *
     * @param hotel       обект от тип {@code Hotel}, в който ще се заредят данните
     * @param fileManager обект от тип {@code FileManager}, който управлява отварянето на файла
     */
    public OpenCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява командата за отваряне на файл.
     * Ако файлът не съществува или възникне грешка, се извежда съобщение.
     * @param args масив от аргументи, където {@code args[1]} трябва да е пътят до файла
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Въведете: open <път до файл>");
            return;
        }

        try {
            fileManager.open(hotel, args[1]);
            System.out.println("Успешно отворен файл: " + args[1]);
        } catch (IOException e) {
            System.out.println("Грешка при отваряне на файл: " + e.getMessage());
        }
    }
}
