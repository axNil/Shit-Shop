package beans.message;

import beans.Order;

public class PendingOrderMessage extends Message {
    public PendingOrderMessage(Order newOrder) {
        super(String.format("Someone wants to buy %s! Approve or decline?", newOrder.getProductID()));
    }
}
