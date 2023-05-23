package beans.message;

import enums.MessageType;
import enums.ProductType;

import java.util.Objects;

public abstract class Message {
    private String text;
    private final MessageType messageType;
    private int productID;
    private ProductType productType;
    private boolean isSent;

    public Message(String text, MessageType messageType, int productID, ProductType productType) {
        this.text = text;
        this.messageType = messageType;
        this.productID = productID;
        this.productType = productType;
    }

    public Message(String text, MessageType messageType, int productID) {
        this(text, messageType, productID, null);
    }

    public Message(String text, MessageType messageType, ProductType productType) {
        this(text, messageType, -1, productType);
    }
    public Message(String text, MessageType messageType) {
        this(text, messageType, -1, null);
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public String getText() {
        return text;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Message message) {
            return Objects.equals(message.text, this.text);
        }
        return false;
    }

    @Override
    public String toString() {
        return (isSent ? "SENT" : "UNSENT") +
                " -- " + text;
    }
}
