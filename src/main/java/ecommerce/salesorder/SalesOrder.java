package ecommerce.salesorder;

import ecommerce.common.Country;

public class SalesOrder {

    private final LineItems lineItems = new LineItems();
    private Country country;

    void addItem(LineItem lineItem) {
        lineItems.addItem(lineItem);
    }

    public int getTotal() {
        return lineItems.totalPrice()
                +
                shippingCost();
    }

    public int shippingCost() {
        if (!country.equals(Country.POLAND)) {
            return internationalShippingCost();
        }
        if (lineItems.onlyBooks()) {
            return booksPromoShippingCost();
        }
        return standardShippingCost();
    }

    private int standardShippingCost() {
        return 15;
    }

    private int booksPromoShippingCost() {
        if (eligibleForFreeBookShipping()) {
            return 0;
        }
        return 5;
    }

    private boolean eligibleForFreeBookShipping() {
        return lineItems.totalPrice() > 200;
    }

    private int internationalShippingCost() {
        if (isHeavy()) {
            return 70;
        }
        return 50;
    }

    private boolean isHeavy() {
        return totalWeight() > 10.0;
    }

    private double totalWeight() {
        return lineItems.totalWeight();
    }

    void deliveryCountry(Country country) {
        this.country = country;
    }
}
