package bg.tu_varna.sit.a4.f23621661;

import java.util.Map;

public class HelpCommand implements Command {
    private final Map<String, Command> commandMap;

    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Команди,които може да използвате:");
        commandMap.keySet().forEach(System.out::println);
        System.out.println("exit");
    }
}
