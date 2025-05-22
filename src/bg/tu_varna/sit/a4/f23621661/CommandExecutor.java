package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Класът {@code CommandExecutor} отговаря за регистриране и изпълнение на команди чрез {@code HashMap}.
 * Реализира централизиран механизъм за обработка на всички потребителски команди чрез шаблона Command.
 */
public class CommandExecutor {

    /**
     * Инстанция на {@code Hotel}, използвана от всички команди.
     */
    private final Hotel hotel = new Hotel();

    /**
     * Мениджър за работа с файлове.
     */
    private final FileManager fileManager = new FileManager();

    /**
     * Хеш-таблица, която съхранява командите по име.
     */
    private final Map<String, Command> commandMap = new HashMap<>();

    /**
     * Конструктор, който инициализира и регистрира всички налични команди.
     */
    public CommandExecutor() {
        registerCommands();
    }

    /**
     * Регистрира всички поддържани команди в {@code commandMap}.
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
     * Изпълнява команда въз основа на потребителски вход.
     * Разделя входния текст по интервали и извиква съответната команда, ако е регистрирана.
     * В противен случай показва съобщение за грешка.
     *
     * @param input входен низ, съдържащ името на командата и аргументи
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
