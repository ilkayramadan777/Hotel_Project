package bg.tu_varna.sit.a4.f23621661;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Hotel hotel = new Hotel();
    private final FileManager fileManager = new FileManager();
    private final Map<String, Command> commands = new HashMap<>();

    public CommandExecutor() {
        loadCommands();
    }

    private void loadCommands() {
        commands.put("open", new OpenCommand(hotel, fileManager));
        commands.put("close", new CloseCommand(hotel, fileManager));
        commands.put("save", new SaveCommand(hotel, fileManager));
        commands.put("saveas", new SaveAsCommand(hotel, fileManager));
        commands.put("checkin", new CheckinCommand(hotel));
        commands.put("checkout", new CheckoutCommand(hotel));
        commands.put("availability", new AvailabilityCommand(hotel));
        commands.put("report", new ReportCommand(hotel));
        commands.put("find", new FindCommand(hotel));
        commands.put("find!", new FindUrgentCommand(hotel));
        commands.put("unavailable", new UnavailableCommand(hotel));
        commands.put("help", new HelpCommand(commands));
        commands.put("exit", new ExitCommand());
    }

    public void execute(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length == 0) return;

        String key = parts[0].toLowerCase();
        Command command = commands.get(key);

        if (command != null) {
            try {
                command.execute(parts);
            } catch (Exception e) {
                System.out.println("Възникна грешка: " + e.getMessage());
            }
        } else {
            System.out.println("Непозната команда. Използвайте help за списък.");
        }
    }
}


