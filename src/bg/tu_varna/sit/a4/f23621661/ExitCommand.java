package bg.tu_varna.sit.a4.f23621661;

/**
 * Класът {@code ExitCommand} представлява команда за излизане от програмата.
 * Част е от реализацията на шаблона Command.
 */
public class ExitCommand implements Command {

    /**
     * Изпълнява командата за излизане от приложението.
     * Извежда съобщение в конзолата и прекратява изпълнението на програмата чрез {@code System.exit(0)}.
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Излизане от програмата...");
        System.exit(0);
    }
}
