package by.itacademy.transports;

public class InvalidTransport {

    private final String type;//используется только внутри класса, нельзя изменить ссылку
    private final String model;//используется только внутри класса, нельзя изменить ссылку

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