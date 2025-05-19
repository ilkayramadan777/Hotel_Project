package bg.tu_varna.sit.a4.f23621661;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Излизане от програмата...");
        System.exit(0);
    }
}


