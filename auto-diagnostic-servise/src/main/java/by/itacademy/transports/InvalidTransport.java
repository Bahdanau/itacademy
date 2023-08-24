package by.itacademy.transports;

public class InvalidTransport {

    private final String type;
    private final String model;

    public InvalidTransport(String type, String model) {
        this.type = type;
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return getType() + '|' + getModel();

    }
}