package bg.tu_varna.sit.a4.f23621661;

import java.util.Map;

/**
 * Класът {@code HelpCommand} представлява команда за извеждане на списък с всички налични команди,
 * регистрирани в системата. Част е от реализацията на шаблона Command.
 */
public class HelpCommand implements Command {

    /**
     * Хеш-таблица с имената на регистрираните команди и съответните им обекти.
     */
    private final Map<String, Command> commandMap;

    /**
     * Конструктор за създаване на команда {@code HelpCommand}.
     *
     * @param commandMap речник с всички налични команди
     */
    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Изпълнява командата за показване на списък с всички регистрирани команди.
     * Командите се извеждат в сортиран вид по азбучен ред.
     *
     * @param args масив от аргументи (не се използват при тази команда)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Списък с поддържани команди:");
        commandMap.keySet().stream()
                .sorted()
                .forEach(cmd -> System.out.println(" - " + cmd));
    }
}
