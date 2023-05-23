package beans.message;

import beans.Order;
import enums.MessageType;

public class PendingOrderMessage extends Message {
    private final int productID;
    public PendingOrderMessage(Order newOrder) {
        super("Someone wants to buy your product! Approve or decline?", MessageType.PENDING);
        productID = newOrder.getProductID();
    }
}
