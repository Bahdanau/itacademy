package by.itacademy.transports;

public class Transport {

    private final String type;
    private final String model;
    private final int price;

    public Transport(String type, String modell, int price) {
        this.type = type;
        this.model = modell;
        this.price = price;
    }

    public String getTransportType() {
        return type;
    }

    public String getTransportModel() {
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

