package bg.tu_varna.sit.a4.f23621661;

public class CheckoutCommand implements Command {
    private Hotel hotel;

    public CheckoutCommand(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: checkout <room>");
            return;
        }

        int room = Integer.parseInt(args[1]);
        hotel.checkout(room);
    }
}

