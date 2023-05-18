package application;

import application.listener.OrderListener;
import beans.Order;
import beans.message.OrderApprovedMessage;
import beans.message.OrderDeclinedMessage;
import beans.message.PendingOrderMessage;
import data.DBI;

import java.util.ArrayList;

public class OrderManager implements OrderListener {
    private final DBI DBI;

    protected OrderManager(DBI db) {
        DBI = db;
    }

    public void placeOrder(String username, int productID) {
        Order newOrder = DBI.addOrder(username, productID);
        onAdd(newOrder);
    }

    public void approveOrder(Order approvedOrder) {
        ArrayList<Order> orders = DBI.getOrders(approvedOrder.getProductID());
        for (Order o : orders) {
            if(o != approvedOrder) {
                o.declineOrder();
                onDecline(o);
            }
        }
        approvedOrder.approveOrder();
        onApprove(approvedOrder);
    }

    public void declineOrder(Order order) {
        order.declineOrder();
        onDecline(order);
    }

    public ArrayList<Order> getOrders(String buyer) {
        return DBI.getOrders(buyer);
    }

    public ArrayList<Order> getOrders(int productID) {
        return DBI.getOrders(productID);
    }

    // This should call the listeners, which then creates messages...
    public void onAdd(Order newOrder) {
        String seller = DBI.getSeller(newOrder.getProductID());
        DBI.addMessage(seller, new PendingOrderMessage(newOrder));
    }

    // This should call the listeners, which then creates messages...
    @Override
    public void onDecline(Order declinedOrder) {
        DBI.addMessage(declinedOrder.getBuyer(), new OrderDeclinedMessage(declinedOrder));
    }

    // This should call the listeners, which then creates messages...
    @Override
    public void onApprove(Order approvedOrder) {
        DBI.addMessage(approvedOrder.getBuyer(), new OrderApprovedMessage(approvedOrder));
    }
}
