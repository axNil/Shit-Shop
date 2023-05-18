package application.listener;

import beans.Product;

// UserManager extend this, use Threads to actually do the thing/send events.
public interface ProductListener {
    void onAdd(Product newProduct);
}
