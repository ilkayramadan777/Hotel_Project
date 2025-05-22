package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

/**
 * Команда за запазване на данните в нов файл.
 * Записва текущото състояние на хотела в зададен път.
 */
public class SaveAsCommand implements Command {

    private final Hotel hotel;
    private final FileManager fileManager;

    /**
     * Създава нова команда за запис в нов файл.
     *
     * @param hotel       хотелският обект
     * @param fileManager мениджър за работа с файлове
     */
    public SaveAsCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява запис на данните в нов файл.
     *
     * @param args аргументи: args[1] – име на файла
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Въведете: saveas <път до нов файл>");
            return;
        }
        try {
            fileManager.saveAs(hotel, args[1]);
            System.out.println("Успешно запазено като: " + args[1]);
        } catch (IOException e) {
            System.out.println("Грешка при запазване с ново име: " + e.getMessage());
        }
    }
}
