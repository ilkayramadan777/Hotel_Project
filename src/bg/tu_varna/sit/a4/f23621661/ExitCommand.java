package bg.tu_varna.sit.a4.f23621661;

/**
 * Команда за изход от приложението.
 * При изпълнение прекратява програмата.
 */
public class ExitCommand implements Command {

    /**
     * Извежда съобщение и спира изпълнението на програмата.
     *
     * @param args аргументи (не се използват)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Излизане от програмата...");
        System.exit(0);
    }
}
