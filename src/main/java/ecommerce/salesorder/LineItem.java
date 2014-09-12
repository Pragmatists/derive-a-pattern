package ecommerce.salesorder;

public class LineItem {
    private int price;
    private Type type;
    private double weight = 0.1;

    public double getWeight() {
        return weight;
    }

    public LineItem price(int price) {
        this.price = price;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public LineItem type(Type type) {
        this.type = type;
        return this;
    }

    public boolean isBook() {
        return type == Type.BOOK;
    }

    public LineItem weight(double weight) {
        this.weight = weight;
        return this;
    }

    public enum Type {BOOK}
}
