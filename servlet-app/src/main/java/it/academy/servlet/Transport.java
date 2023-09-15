package it.academy.servlet;

public class Transport {
    private final String model;
    private final String type;

    public Transport(String model, String type) {
        this.model = model;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
