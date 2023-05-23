package beans.message;

import beans.Order;
import enums.MessageType;

public class OrderApprovedMessage extends Message {
    private final int productID;
    public OrderApprovedMessage(Order approvedOrder) {
        super("Your order has been approved.", MessageType.APPROVED);
        productID = approvedOrder.getProductID();
    }
}
