package application;

import application.listener.OrderListener;
import application.listener.ProductListener;
import beans.Order;
import beans.message.OrderApprovedMessage;
import beans.message.OrderDeclinedMessage;
import beans.message.PendingOrderMessage;
import data.DBI;
import enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements OrderListener {
    private final DBI DBI;

    private final ArrayList<OrderListener> listeners = new ArrayList<>();
    public void addListener(OrderListener listener) {
        listeners.add(listener);
    }

    protected OrderManager(DBI db) {
        DBI = db;
    }

    public boolean placeOrder(String username, int productID) {
        Order newOrder = DBI.addOrder(username, productID);

        //order created
        if(newOrder != null) {
            onAdd(newOrder);
            return true;
        }
        //order not created
        return false;
    }

    public void approveOrder(Order approvedOrder) {
        ArrayList<Order> orders = DBI.getOrders(approvedOrder.getProductID());
        for (Order o : orders) {
            if(!o.equals(approvedOrder)) {
                o.declineOrder();
                onDecline(o);
            } else {
                o.approveOrder();
                onApprove(o);
            }
        }
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
        DBI.updateOrder(declinedOrder);
        DBI.addMessage(declinedOrder.getBuyer(), new OrderDeclinedMessage(declinedOrder));

        new Thread(()-> {
            for (OrderListener l : listeners) {
                l.onDecline(declinedOrder);
            }
        }).start();
    }

    // This should call the listeners, which then creates messages...
    @Override
    public void onApprove(Order approvedOrder) {
        DBI.updateOrder(approvedOrder);
        DBI.addMessage(approvedOrder.getBuyer(), new OrderApprovedMessage(approvedOrder));

        new Thread(()-> {
            for (OrderListener l : listeners) {
                l.onApprove(approvedOrder);
            }
        }).start();
    }

    public List<Order> getOrdersBySeller(String username) {
        return DBI.getOrdersBySeller(username);
    }

    public ArrayList<Order> getApprovedOrders(String username, LocalDate from, LocalDate to) {
        ArrayList<Order> response = new ArrayList<>();
        ArrayList<Order> orders = DBI.getOrders(username);
        for (Order o : orders) {
            if (o.getStatus() == OrderStatus.APPROVED) {
                LocalDate processedDate = LocalDateTime.parse(o.getProcessedDate()).toLocalDate();
                if (processedDate.isAfter(from) || processedDate.isEqual(from)) {
                    if (processedDate.isBefore(to) || processedDate.isEqual(to)) {
                        response.add(o);
                    }
                }
            }
        }
        return response;
    }
}
