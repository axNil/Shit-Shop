package beans.message;

import beans.Order;
import enums.MessageType;

public class OrderApprovedMessage extends Message {
    public OrderApprovedMessage(Order approvedOrder) {
        super("Your order has been approved.", MessageType.APPROVED, approvedOrder.getProductID());
    }


}
