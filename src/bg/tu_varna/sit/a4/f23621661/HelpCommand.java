package bg.tu_varna.sit.a4.f23621661;

import java.util.Map;

/**
 * Команда за извеждане на списък с всички налични команди в системата.
 * Командите се сортират и показват в азбучен ред.
 */
public class HelpCommand implements Command {

    /**
     * Речник с имената и обектите на регистрираните команди.
     */
    private final Map<String, Command> commandMap;

    /**
     * Създава нова help команда.
     *
     * @param commandMap списък с всички налични команди
     */
    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Извежда списък с всички регистрирани команди.
     *
     * @param args аргументи (не се използват)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Списък с поддържани команди:");
        commandMap.keySet().stream()
                .sorted()
                .forEach(cmd -> System.out.println(" - " + cmd));
    }
}
