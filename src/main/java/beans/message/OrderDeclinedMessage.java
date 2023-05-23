package beans.message;

import beans.Order;
import enums.MessageType;

public class OrderDeclinedMessage extends Message {
    private final int productID;
    public OrderDeclinedMessage(Order approvedOrder) {
        super("Your order has been declined.", MessageType.DECLINED);
        productID = approvedOrder.getProductID();
    }
}
