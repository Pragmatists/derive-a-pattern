package ecommerce.salesorder;

import ecommerce.common.Country;

public class SalesOrder {

    private final LineItems lineItems = new LineItems();
    private Country country;

    void addItem(LineItem lineItem) {
        lineItems.addItem(lineItem);
    }

    public int getTotal() {
        return totalPrice()
                +
                createShippingCostStrategy().shippingCost();
    }

    private ShippingCostStrategy createShippingCostStrategy() {
        if (isInternational()) {
            return new InternationalShippingCostStrategy(this);
        }
        if (hasOnlyBooks()) {
            return new BooksPromoShippingCostStrategy(this);
        }

        return new StandardShippingCostStrategy(this);
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
