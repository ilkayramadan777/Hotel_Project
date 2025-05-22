package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

/**
 * Команда за отваряне на файл с информация за хотел.
 * Зарежда стаи и резервации в обекта на хотела.
 */
public class OpenCommand implements Command {

    /**
     * Хотелът, в който ще се зареждат данните.
     */
    private final Hotel hotel;

    /**
     * Мениджър за файлови операции.
     */
    private final FileManager fileManager;

    /**
     * Създава нова команда за отваряне на файл.
     *
     * @param hotel       хотелският обект
     * @param fileManager мениджър за работа с файлове
     */
    public OpenCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява зареждане на файл.
     * Ако няма подадено име или има грешка при четене – извежда съобщение.
     *
     * @param args аргументи: args[1] – име или път на файла
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
