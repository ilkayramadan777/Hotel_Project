package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Hotel hotel = new Hotel();
    private final FileManager fileManager = new FileManager();
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandExecutor() {
        registerCommands();
    }

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

}

