package data;

import beans.*;
import beans.message.Message;
import beans.message.WishlistMessage;
import enums.Color;
import enums.Condition;
import enums.OrderStatus;
import enums.ProductType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    // User info
    //Username, user
    private final ConcurrentHashMap<String, User> users;

    // Inbox
    // username, message
    private final ConcurrentHashMap<String, ArrayList<Message>> userMessages;

    // Wishlist
    // productType - user_id
    private final ConcurrentHashMap<ProductType, ArrayList<String>> wishlistSubscriptions;

    // Product listings
    //productID, product
    private final ConcurrentHashMap<Integer, Product> products;

    // Placed orders
    private final ArrayList<Order> orders;

    ////////////// Fake Query Language //////////////
    public Collection<Product> selectAllProducts() {
        return products.values();
    }

    public ArrayList<String> selectAllSubscribers(ProductType productType) {
        return wishlistSubscriptions.get(productType);
    }

    public ArrayList<Message> selectMessages(String username) {
        return userMessages.get(username);
    }

    public boolean updateMessage(String username, Message updatedMessage) {
        ArrayList<Message> inbox = userMessages.get(username);
        if (inbox == null) return false;

        for (int i = 0; i < inbox.size(); i++) {
            if(inbox.get(i).equals(updatedMessage)) {
                inbox.set(i, updatedMessage);
                return true;
            }
        }
        return false;
    }

    public Collection<User> selectAllUsers() {
        return users.values();
    }

    public User selectUser(String username) {
        return users.getOrDefault(username, null);
    }

    public String updateUser(String username, User newUserData) {
        users.put(username, newUserData);
        return username;
    }

    public Order updateOrder(Order newOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).equals(newOrder)) {
                orders.set(i, newOrder);
                return newOrder;
            }
        }
        return null;
    }

    public Order getOrder(int productID, String buyer) {
        Order o;
        for (int i = 0; i < orders.size(); i++) {
            o = orders.get(i);
            if (o.getProductID() == productID && o.getBuyer().equals(buyer))
                return o;

        }
        return null;
    }

    public Collection<Order> selectAllOrders() {
        return orders;
    }

    public ArrayList<Order> selectProductsOrders(int product_id) {
        ArrayList<Order> productOrders = new ArrayList<>();
        for (Order o : orders) {
            if(o.getProductID() == product_id) {
                productOrders.add(o);
            }
        }
        return productOrders;
    }

    public void setProductToSold(int product_id) {
        products.get(product_id).setSold(true);
    }

    public ArrayList<Order> selectOrders(String buyer) {
        ArrayList<Order> productOrders = new ArrayList<>();
        for (Order o : orders) {
            if(o.getBuyer().equals(buyer)) {
                productOrders.add(o);
            }
        }
        return productOrders;
    }


    public List<Order> selectOrdersWhereSeller(String username) {
        List<Order> orders = new ArrayList<>();
        List<Integer> product_ids = new ArrayList<>();

        for (Product p : products.values()) {
            if(p.getSeller().equals(username)) {
                product_ids.add(p.getProductID());
            }
        }

        for (Integer pid : product_ids) {
            List<Order> product_orders = selectProductsOrders(pid);
            for (Order o : product_orders) {
                if(o.getStatus() != OrderStatus.DECLINED) {
                    orders.add(o);
                }
            }
        }

        return orders;
    }

    public String getSeller(int product_id) {
        return products.get(product_id).getSeller();
    }

    /**
     * Add product to DB
     * @param product
     * @return id of product
     */
    public int addProduct(Product product) {
        int p_id = products.size();
        product.setProductID(p_id);
        products.put(p_id, product);
        return p_id;
    }

    /**
     * Add user
     * @param user
     * @return Username of added user
     */
    public String addUser(User user) {
        users.put(user.getUsername(), user);
        return user.getUsername();
    }

    public void addWishlistSubscribtion(ProductType pt, String username) {
        if (wishlistSubscriptions.containsKey(pt)) {
            wishlistSubscriptions.get(pt).add(username);
        } else {
            wishlistSubscriptions.put(pt, new ArrayList<>(List.of(username)));
        }
    }

    public Order placeOrder(String username, int product_id) {
        Order order = new Order(username, product_id);
        if(orders.contains(order)) {
            return null;
        }
        orders.add(order);
        return order;
    }

    public void addMessage(String username, Message message) {
        ArrayList<Message> inbox = userMessages.get(username);
        if(inbox == null) {
            inbox = new ArrayList<>();
            userMessages.put(username, inbox);
        }
        inbox.add(message);
    }

    //// Init DB ////
    public Database() {
        //initProductID();
        orders = new ArrayList<>();
        users = new ConcurrentHashMap<>();
        userMessages = new ConcurrentHashMap<>();
        wishlistSubscriptions = new ConcurrentHashMap<>();
        products = new ConcurrentHashMap<>();

        fill();
    }

    private void fill() {
        users.put("admin", new User(
                "admin", "admin",
                "2023-05-12", "admin@shitshop.com",
                "admin", "admin"));
        users.put("user1", new User(
                "Kalle", "Kalas",
                "2023-05-18", "email@email.com",
                "user1", "user1"));
        users.put("user2", new User(
                "Bolla", "Bombastic",
                "2023-05-99", "mina@brev.net",
                "user2", "user2"));

        users.put("sven", new User(
                "Bolla", "Bombastic",
                "2000-05-05", "mina@brev.net",
                "sven", "sven"));
        users.put("jan", new User(
                "Bolla", "Bombastic",
                "2000-05-05", "mina@brev.net",
                "jan", "jan"));

        userMessages.put("user1", new ArrayList<>(Arrays.asList(
                new WishlistMessage(ProductType.LAWNMOWER),
                new WishlistMessage(ProductType.ROBOT)
        )));

        wishlistSubscriptions.put(ProductType.STEEL, new ArrayList<>(List.of("user1")));

        products.put(1000, new Product(ProductType.CHEESE, "Flens", 24, 2023, Color.UNSPECIFIED, Condition.DEFECT, "user1", 1000, ""));
        products.put(1001, new Product(ProductType.LAPTOP, "Flenky", 10, 2023, Color.UNSPECIFIED, Condition.DEFECT, "user1", 1001, ""));

        orders.add(new Order("user2", 1000));
    }

    public Product selectProductByID(int product_id) {
        return products.get(product_id);
    }
}
