package ecommerce.salesorder;

public class InternationalShippingCostStrategy extends ShippingCostStrategy {
    public InternationalShippingCostStrategy(SalesOrder salesOrder) {
        super(salesOrder);
    }

    @Override
    public int shippingCost() {
        return internationalShippingCost();
    }

    boolean isHeavy() {
        return salesOrder.totalWeight() > 10.0;
    }

    int internationalShippingCost() {
        if (isHeavy()) {
            return 70;
        }
        return 50;
    }

}
