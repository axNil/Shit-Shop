package application;

import beans.Product;

// UserManager extend this, use Threads to actually do the thing/send events.
public interface ProductListener {
    public void onAdd(Product newProduct);
}
