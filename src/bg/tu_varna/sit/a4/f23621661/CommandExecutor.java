package bg.tu_varna.sit.a4.f23621661;

import java.util.HashMap;
import java.util.Map;


public class CommandExecutor {
    private final Hotel hotel = new Hotel();
    private final FileManager fileManager = new FileManager();
    private final Map<String, Runnable> commandMap = new HashMap<>();
    private String[] args;
}

