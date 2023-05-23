package beans.message;

import beans.Order;
import enums.MessageType;

public class OrderDeclinedMessage extends Message {
    public OrderDeclinedMessage(Order approvedOrder) {
        super("Your order has been declined.", MessageType.DECLINED, approvedOrder.getProductID());
    }
}
