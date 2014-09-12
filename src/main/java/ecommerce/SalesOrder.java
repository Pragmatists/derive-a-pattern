package ecommerce;

public class SalesOrder {

    private final LineItems lineItems = new LineItems();

    public void addItem(LineItem lineItem) {
        lineItems.addItem(lineItem);
    }

    public int getTotal() {
        return lineItems.itemsTotal()
                +
                shippingCost();
    }

    private int shippingCost() {
        if (lineItems.itemsTotal() > 200 && lineItems.onlyBooks()) {
            return 0;
        }
        return 15;
    }

}
