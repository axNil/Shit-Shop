package application.listener;

import beans.Order;

public interface OrderListener {
    void onAdd(Order newOrder);
    void onDecline(Order declinedOrder);
    void onApprove(Order approvedOrder);
}
