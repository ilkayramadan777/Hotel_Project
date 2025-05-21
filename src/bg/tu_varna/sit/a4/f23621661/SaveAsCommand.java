package bg.tu_varna.sit.a4.f23621661;

import java.io.IOException;

/**
 * Класът {@code SaveAsCommand} представлява команда за запазване на текущите
 * хотелски данни в нов файл. Част е от реализацията на шаблона Command.
 */
public class SaveAsCommand implements Command {

    /**
     * Обект от тип {@code Hotel}, чиито данни ще се записват.
     */
    private final Hotel hotel;

    /**
     * Мениджър за работа с файлове.
     */
    private final FileManager fileManager;

    /**
     * Конструктор за създаване на команда {@code SaveAsCommand}.
     *
     * @param hotel       хотелът, чиито данни се записват
     * @param fileManager файлов мениджър, който извършва записа
     */
    public SaveAsCommand(Hotel hotel, FileManager fileManager) {
        this.hotel = hotel;
        this.fileManager = fileManager;
    }

    /**
     * Изпълнява командата за запис на данните в нов файл.
     * При грешка при записване извежда съответно съобщение.
     * @param args масив от аргументи, където {@code args[1]} съдържа името на новия файл
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
