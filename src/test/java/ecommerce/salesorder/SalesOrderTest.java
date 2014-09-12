package ecommerce.salesorder;

import ecommerce.common.Country;
import org.junit.Test;

import static ecommerce.salesorder.LineItem.Type.*;
import static org.assertj.core.api.Assertions.*;

public class SalesOrderTest {

    @Test
    public void totalCost_ofOrderWithOneItem_isPricePlusShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder().withItemPriced(100).build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(115);
    }

    @Test
    public void totalCost_ofOrderWithTwoItems_isSumOfPricesPlusShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .withItemPriced(100)
                .withItemPriced(100)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(215);
    }

    @Test
    public void totalCost_ofBooksOnly_hasReducedShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .withBookPriced(200)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(205);
    }

    @Test
    public void totalCost_ofBooksOnlyWorthMoreThan200_hasFreeShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .withBookPriced(100)
                .withBookPriced(110)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(210);
    }

    @Test
    public void totalCost_ofBooksAndOtherItemsWorthMoreThan200_hasNormalShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .withItemPriced(100)
                .withBookPriced(110)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(225);
    }

    @Test
    public void totalCost_ofOrderShippedOutsidePoland_includesInternationalShippingCost() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .deliverTo(Country.GERMANY)
                .withItemPriced(100)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(150);
    }

    @Test
    public void totalCost_ofOrderShippedOutsidePolandEligibleForBookPromo_hasInternationalShippingCost() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .deliverTo(Country.GERMANY)
                .withBookPriced(300)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(350);
    }

    @Test
    public void totalCost_ofHeavyItemsOutsidePoland_hasIncreasedShippingCost() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .deliverTo(Country.GERMANY)
                .withItem(new LineItem().price(300).weight(15.0))
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(370);

    }

    private class SalesOrderBuilder {

        SalesOrder salesOrder = new SalesOrder();

        public SalesOrderBuilder() {
            salesOrder.deliveryCountry(Country.POLAND);
        }

        public SalesOrderBuilder withItemPriced(int price) {
            return withItem(new LineItem().price(price));
        }

        public SalesOrder build() {
            return salesOrder;
        }

        public SalesOrderBuilder withBookPriced(int price) {
            return withItem(new LineItem().price(price).type(BOOK));
        }

        public SalesOrderBuilder deliverTo(Country country) {
            salesOrder.deliveryCountry(country);
            return this;
        }

        public SalesOrderBuilder withItem(LineItem lineItem) {
            salesOrder.addItem(lineItem);
            return this;
        }
    }
}
