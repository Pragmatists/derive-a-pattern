package ecommerce.salesorder;

public class StandardShippingCostStrategy extends ShippingCostStrategy {
    public StandardShippingCostStrategy(SalesOrder salesOrder) {
        super(salesOrder);
    }

    @Override
    public int shippingCost() {
        return 15;
    }

}
