package by.itacademy.transports;

public class ProcessedTransport {

    private final String type;
    private final String model;
    private final int price;

    public ProcessedTransport(String type, String model, int price) {
        this.type = type;
        this.model = model;
        this.price = price;
    }


    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}