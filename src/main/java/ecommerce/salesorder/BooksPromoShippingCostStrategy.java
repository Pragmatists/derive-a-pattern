package ecommerce.salesorder;

public class BooksPromoShippingCostStrategy extends ShippingCostStrategy {
    public BooksPromoShippingCostStrategy(SalesOrder salesOrder) {
        super(salesOrder);
    }

    @Override
    public int shippingCost() {
        return booksPromoShippingCost();
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

}
