package beans.message;

import java.util.Objects;

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
