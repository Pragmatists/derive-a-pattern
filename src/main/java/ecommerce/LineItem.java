package ecommerce;

public class LineItem {
    private int price;
    private Type type;

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

    public enum Type {BOOK}
}
