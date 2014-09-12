package ecommerce;

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

    Integer itemsTotal() {
        return lineItems.stream()
                .map(LineItem::getPrice)
                .reduce(Integer::sum).get();
    }
}