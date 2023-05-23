package beans.message;

import beans.message.Message;
import enums.MessageType;
import enums.ProductType;

public class WishlistMessage extends Message {
    private final ProductType productType;
    public WishlistMessage(ProductType productType) {
        super(String.format("New products have been added in the %s category.", productType.toString()), MessageType.WISHLIST);
        this.productType = productType;
    }
}
