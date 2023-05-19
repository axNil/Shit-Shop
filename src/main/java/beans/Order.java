package beans;

import enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private final String buyer;
    private final int product_id;
    private OrderStatus status;
    private final String orderedDate;
    private String processedDate;

    public Order(String buyer, int product_id) {
        this.buyer = buyer;
        this.product_id = product_id;
        status = OrderStatus.PENDING;
        orderedDate = LocalDateTime.now().toString();
    }

    public String getOrderedDate() { return orderedDate; }
    public String getProcessedDate() { return processedDate; }
    public OrderStatus getStatus() { return status; }
    public String getBuyer() { return buyer; }
    public int getProductID() { return product_id; }

    public void approveOrder() {
        if(processedDate == null || processedDate.equals("")) {
            status = OrderStatus.APPROVED;
            processedDate = LocalDateTime.now().toString();
        }
    }
    public void declineOrder() {
        if(processedDate == null || processedDate.equals("")) {
            status = OrderStatus.DECLINED;
            processedDate = LocalDateTime.now().toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order order)
            return product_id == order.getProductID() && Objects.equals(buyer, order.getBuyer());

        return false;
    }
}
