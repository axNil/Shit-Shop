package beans.message;

import beans.Order;

public class OrderDeclinedMessage extends Message {
    public OrderDeclinedMessage(Order approvedOrder) {
        super(String.format("Your order for %s has been declined.", approvedOrder.getProductID()));
    }
}
