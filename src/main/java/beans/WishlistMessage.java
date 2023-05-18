package beans;

import beans.message.Message;
import enums.ProductType;

public class WishlistMessage extends Message {
    public WishlistMessage(ProductType productType) {
        super(String.format("New products have been added in the %s category.", productType.toString()));
    }
}
