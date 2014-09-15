package ecommerce.salesorder;

public class ShippingCostStrategy {
    private SalesOrder salesOrder;

    public ShippingCostStrategy(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
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

    boolean eligibleForFreeBookShipping() {
        return salesOrder.totalPrice() > 200;
    }

    int booksPromoShippingCost() {
        if (eligibleForFreeBookShipping()) {
            return 0;
        }
        return 5;
    }

    int standardShippingCost() {
        return 15;
    }

    public int shippingCost() {
        if (salesOrder.isInternational()) {
            return internationalShippingCost();
        }
        if (salesOrder.hasOnlyBooks()) {
            return booksPromoShippingCost();
        }
        return standardShippingCost();
    }
}
