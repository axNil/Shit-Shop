package data;

import application.ProductTypeSubscriber;
import beans.Product;
import beans.User;
import enums.Color;
import enums.Condition;
import enums.ProductType;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {
    private AtomicInteger productIDs;

    //Username, user
    private ConcurrentHashMap<String, User> users;

    //username, token
    private ConcurrentHashMap<String, String> connectedUsers;

    //productType, observer(user)
    private ConcurrentHashMap<ProductType, LinkedList<ProductTypeSubscriber>> wishlistSubscriptions;

    //productID, product
    private ConcurrentHashMap<Integer, Product> products;

    public Database() {
        initProductID();
        users = new ConcurrentHashMap<>();
        connectedUsers = new ConcurrentHashMap<>();
        wishlistSubscriptions = new ConcurrentHashMap<>();
        products = new ConcurrentHashMap<>();

        fill();
    }

    public boolean checkIfUsernameExist(String username) {
        return users.containsKey(username);
    }

    public boolean checkIfEmailExist(String email) {
        for (User u : users.values())
            if (u.getEmail().equals(email)) return true;

        return false;
    }

    public void addSubscriber(ProductType pt, String username) {
        User user = users.get(username);
        if (wishlistSubscriptions.containsKey(pt)) {
            wishlistSubscriptions.get(pt).add(user);
        } else {
            wishlistSubscriptions.put(pt, new LinkedList<>(List.of(user)));
        }
    }

    public String getPassword(String username) {
        return users.get(username).getPassword();
    }

    public List<Product> getProducts() {
        return products.values().stream().toList();
    }

    public void setProducts(ConcurrentHashMap<Integer, Product> products) {
        this.products = products;
    }

    public boolean addToConnectedUsers(String user, String token) {
        connectedUsers.put(user, token);
        return true;
    }

    public void addUser(String username, User user) {
        users.put(username, user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    private void fill() {
        products.put(1000, new Product(ProductType.CHEESE, "Flens", 24, 2023, Color.UNSPECIFIED, Condition.DEFECT, "Viktor", 1000));
        products.put(1001, new Product(ProductType.LAPTOP, "Flenky", 10, 2023, Color.UNSPECIFIED, Condition.DEFECT, "Viktor", 1001));
        users.put("admin", new User("admin", "admin", "2023-05-12", "admin@shitshop.com", "admin", "admin"));
    }

    private void initProductID() {
        try (Scanner sc = new Scanner(new File("src/main/resources/data/productIDs.txt"))) {
            int pid = sc.nextInt();
            productIDs = new AtomicInteger(pid);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("ProductID read no good");
        }
    }

    private void writeProductID() {
        try (FileWriter fw = new FileWriter("src/main/resources/data/productIDs.txt")){
            fw.write(productIDs.toString());
        } catch (IOException e) {
            throw new RuntimeException("ProductID write no good");
        }
    }

    public int getProductID() {
        return productIDs.getAndIncrement();
    }

    public void addProduct(Product product) {
        products.put(product.getProductID(), product);
        writeProductID();
    }

    public LinkedList<ProductTypeSubscriber> getSubscribers(ProductType productType) {
        return wishlistSubscriptions.get(productType);
    }
}
