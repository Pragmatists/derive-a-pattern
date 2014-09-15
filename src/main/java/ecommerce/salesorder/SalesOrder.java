package ecommerce.salesorder;

import ecommerce.common.Country;

public class SalesOrder {

    private final LineItems lineItems = new LineItems();
    private Country country;
    private ShippingCostStrategy shippingCostStrategy = new ShippingCostStrategy(this);

    void addItem(LineItem lineItem) {
        lineItems.addItem(lineItem);
    }

    public int getTotal() {
        return totalPrice()
                +
                shippingCostStrategy.shippingCost();
    }

    boolean isInternational() {
        return !country.equals(Country.POLAND);
    }

    boolean hasOnlyBooks() {
        return lineItems.onlyBooks();
    }

    Integer totalPrice() {
        return lineItems.totalPrice();
    }

    double totalWeight() {
        return lineItems.totalWeight();
    }

    void deliveryCountry(Country country) {
        this.country = country;
    }
}
