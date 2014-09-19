package ecommerce.salesorder;

public abstract class ShippingCostStrategy {
    protected SalesOrder salesOrder;

    public ShippingCostStrategy(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public abstract int shippingCost();
}
