package beans;

public abstract class Message {
    private String text;
    private boolean isSent;

    public Message(String text) {
        this.text = text;
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

    public void setText(String text) {
        this.text = text;
    }
}
