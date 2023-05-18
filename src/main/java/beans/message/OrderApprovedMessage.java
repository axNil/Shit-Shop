package beans.message;

import beans.Order;

public class OrderApprovedMessage extends Message {
    public OrderApprovedMessage(Order approvedOrder) {
        super(String.format("Your order has been approved. You are the new owner of " + approvedOrder.getProductID()));
    }
}
