package beans;

import enums.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private final String username;
    private final int product_id;
    private OrderStatus status;
    private final String orderedDate;
    private String processedDate = "";

    public Order(String username, int product_id) {
        this.username = username;
        this.product_id = product_id;
        status = OrderStatus.PENDING;
        orderedDate = LocalDateTime.now().toString();
    }

    public String getOrderedDate() { return orderedDate; }
    public String getProcessedDate() { return processedDate; }
    public OrderStatus getStatus() { return status; }
    public String getUsername() { return username; }
    public int getProductID() { return product_id; }

    public void approveOrder() {
        if(processedDate == "") {
            status = OrderStatus.APPROVED;
            processedDate = LocalDateTime.now().toString();
        }
    }
    public void declineOrder() {
        if(processedDate == "") {
            status = OrderStatus.DECLINED;
            processedDate = LocalDateTime.now().toString();
        }
    }
}
