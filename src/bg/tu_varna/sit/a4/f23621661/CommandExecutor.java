package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Отговаря за регистриране и изпълнение на всички команди в приложението.
 * Използва шаблона Command с помощта на хеш-таблица за бърз достъп.
 */
public class CommandExecutor {

    /**
     * Хотелът, с който работят командите.
     */
    private final Hotel hotel = new Hotel();

    /**
     * Мениджър за файлови операции.
     */
    private final FileManager fileManager = new FileManager();

    /**
     * Таблица с всички налични команди по име.
     */
    private final Map<String, Command> commandMap = new HashMap<>();

    /**
     * Създава нов изпълнител и регистрира всички команди.
     */
    public CommandExecutor() {
        registerCommands();
    }

    /**
     * Регистрира всички команди в системата.
     */
    private void registerCommands() {
        commandMap.put("open", new OpenCommand(hotel, fileManager));
        commandMap.put("close", new CloseCommand(hotel, fileManager));
        commandMap.put("save", new SaveCommand(hotel, fileManager));
        commandMap.put("saveas", new SaveAsCommand(hotel, fileManager));
        commandMap.put("checkin", new CheckinCommand(hotel));
        commandMap.put("checkout", new CheckoutCommand(hotel));
        commandMap.put("availability", new AvailabilityCommand(hotel));
        commandMap.put("report", new ReportCommand(hotel));
        commandMap.put("find", new FindCommand(hotel));
        commandMap.put("find!", new FindUrgentCommand(hotel));
        commandMap.put("unavailable", new UnavailableCommand(hotel));
        commandMap.put("help", new HelpCommand(commandMap));
        commandMap.put("exit", new ExitCommand());
    }

    /**
     * Изпълнява подадена команда.
     * Ако не е зареден файл, се позволяват само "open", "help" и "exit".
     *
     * @param input въведената от потребителя команда с аргументи
     */
    public void execute(String input) {
        String[] args = input.split("\\s+");
        String cmd = args[0].toLowerCase();
        Command action = commandMap.get(cmd);

        if (!fileManager.isFileOpened() &&
                !cmd.equals("open") &&
                !cmd.equals("exit") &&
                !cmd.equals("help")) {
            System.out.println("Грешка: Моля първо отворете файл с командата 'open'.");
            return;
        }

        if (action != null) {
            try {
                action.execute(args);
            } catch (Exception e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        } else {
            System.out.println("Неизвестна команда. Въведете „help“ за командите, които може да използвате.");
        }
    }
}
