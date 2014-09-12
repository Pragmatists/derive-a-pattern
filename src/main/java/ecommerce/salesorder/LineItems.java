package ecommerce.salesorder;

import java.util.ArrayList;
import java.util.List;

public class LineItems {
    List<LineItem> lineItems = new ArrayList<>();

    public LineItems() {
    }

    public void addItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    boolean onlyBooks() {
        return lineItems.stream().allMatch(LineItem::isBook);
    }

    Integer totalPrice() {
        return lineItems.stream()
                .map(LineItem::getPrice)
                .reduce(Integer::sum).get();
    }

    public double totalWeight() {
        return lineItems.stream()
                .map(LineItem::getWeight)
                .reduce(Double::sum).get();
    }
}