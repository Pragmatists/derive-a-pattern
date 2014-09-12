package ecommerce;

import org.junit.Test;

import static ecommerce.LineItem.Type.*;
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
    public void totalCost_ofBooksOnlyWorthMoreThan200_hasFreeShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .withBookPriced(100)
                .withBookPriced(110)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(210);
    }

    @Test
    public void totalCost_ofBooksOnlyWorth200orLess_hasNormalShipping() {
        SalesOrder salesOrder = new SalesOrderBuilder()
                .withBookPriced(100)
                .withBookPriced(100)
                .build();

        int total = salesOrder.getTotal();

        assertThat(total).isEqualTo(215);
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

    private class SalesOrderBuilder {

        SalesOrder salesOrder = new SalesOrder();

        public SalesOrderBuilder withItemPriced(int price) {
            salesOrder.addItem(new LineItem().price(price));
            return this;
        }

        public SalesOrder build() {
            return salesOrder;
        }

        public SalesOrderBuilder withBookPriced(int price) {
            salesOrder.addItem(new LineItem().price(price).type(BOOK));
            return this;
        }
    }
}
