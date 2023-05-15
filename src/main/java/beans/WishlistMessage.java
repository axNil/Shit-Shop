package beans;

public class WishlistMessage extends Message{
    private Product product;

    public WishlistMessage(Product product) {
        super(String.format("New products have been added in the %s category.", product.getProductType().toString()));
        this.product = product;
    }
}
