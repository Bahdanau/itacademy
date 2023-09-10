package by.itacademy.transports;

public enum TransportType {

    MOTORCYCLE(10),
    AUTOMOBILE(20),
    MINIBUS(30);
    private final Integer price;//используется только Enam, нельзя изменить ссылку

    TransportType(final Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
