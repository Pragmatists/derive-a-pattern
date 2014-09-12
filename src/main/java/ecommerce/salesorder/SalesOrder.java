package ecommerce.salesorder;

import ecommerce.common.Country;
import ecommerce.common.VatId;

public class SalesOrder {

    private final LineItems lineItems = new LineItems();
    private Country country;
    private VatId vatId;

    public void addItem(LineItem lineItem) {
        lineItems.addItem(lineItem);
    }

    public int getTotal() {
        return applyVatIfNecessary(
                lineItems.totalPrice()
                        +
                        shippingCost());
    }

    private int applyVatIfNecessary(int netTotal) {
        if (vatId.isValid()) {
            return netTotal;
        }
        return (int) (1.23 * netTotal);
    }

    private int shippingCost() {
        if (!country.equals(Country.POLAND)) {
            if (totalWeight() > 10.0) {
                return 70;
            }
            return 50;
        }
        if (lineItems.onlyBooks()) {
            if (lineItems.totalPrice() > 200) {
                return 0;
            }
            return 5;
        }
        return 15;
    }

    private double totalWeight() {
        return lineItems.totalWeight();
    }

    public void deliveryCountry(Country country) {
        this.country = country;
    }

    public void customerVatId(VatId vatId) {
        this.vatId = vatId;
    }
}
