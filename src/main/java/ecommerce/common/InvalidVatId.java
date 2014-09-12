package ecommerce.common;

public class InvalidVatId extends VatId {
    public InvalidVatId() {
        super(null, null);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
