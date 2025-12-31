import java.util.*;
import java.io.*;

class Features {
    private ArrayList<String> items;
    private ArrayList<Integer> quantity;
    private int bill;

    public Features() {
        items = new ArrayList<>();
        quantity = new ArrayList<>();
        bill = 0;
    }

    // to get the bill
    public int getTotal() {
        return bill;
    }

    //add items to cart
    public void addOrder(String Item, int quantityOf, String[] things, int[] cost) {
    items.add(Item);
    quantity.add(quantityOf);
    for (int i = 0; i < things.length; i++) {
        if (Item.equals(things[i])) {   // exact match
            bill += quantityOf * cost[i];
            break; // stop once matched
        }
    }
}

    // remove order
    public void removeOrder(String Item, String[] things, int[] cost) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(Item)) {
                // reduce bill
                for (int j = 0; j < things.length; j++) {
                    if (Item.equalsIgnoreCase(things[j].trim())) {
                        bill -= quantity.get(i) * cost[j];
                    }
                }
                items.remove(i);
                quantity.remove(i);
                System.out.println(Item + " removed from cart.");
                return;
            }
        }
        System.out.println("Item not found in cart.");
    }

    // empty cart
    public void emptyCart() {
        items.clear();
        quantity.clear();
        bill = 0;
        System.out.println("Cart emptied successfully.");
    }

    // display orders
    public void getOrder() {
        System.out.println("\n--- Your Orders ---");
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println(items.get(i) + " - " + quantity.get(i));
            }
        }
    }

    // save cart to file
    public void saveCartToFile() {
        try {
            FileWriter fw = new FileWriter("cart.txt");
            fw.write("--- Shopping Cart ---\n");
            for (int i = 0; i < items.size(); i++) {
                fw.write(items.get(i) + " - " + quantity.get(i) + "\n");
            }
            fw.write("Total Bill: Rs." + bill + "\n");
            fw.close();
            System.out.println("Cart saved to cart.txt successfully.");
        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }
}

public class Shoppingcart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] things = {
            "Keychain (metal)", 
            "Wallet (slim design)", 
            "Umbrella (foldable)",
            "Water bottle (stainless steel)", 
            "Perfume (floral, 50ml)",
            "Moisturizer (hydrating cream, 50ml)",
            "Sunscreen (SPF 50, 100ml)",
            "Saree", 
            "Salwar Kameez", 
            "Lehenga Choli", 
            "Kurti", 
            "Dupatta",
            "T-shirt", 
            "Jeans", 
            "Sneakers", 
            "Jacket"
        };

        int[] cost = {
            99,   // Keychain
            79,   // Wallet
            49,   // Umbrella
            149,  // Water bottle
            299,  // Perfume
            199,  // Moisturizer
            249,  // Sunscreen
            999,  // Saree
            799,  // Salwar Kameez
            1499, // Lehenga Choli
            499,  // Kurti
            199,  // Dupatta
            299,  // T-shirt
            899,  // Jeans
            1299, // Sneakers
            1599  // Jacket
        };

        Features cart = new Features();

        int choice;
        while (true) {
            System.out.println("\n--- Shopping Menu ---");
            System.out.println("1. Add items to shopping cart\n2. View cart\n3. Remove item from cart\n4. Make the cart empty\n5. View the bill\n6. Save cart to file\n7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Available items:");
                    for (int i = 0; i < things.length; i++) {
                        System.out.println((i + 1) + ". " + things[i] + " - Rs." + cost[i]);
                    }
                    System.out.print("Enter item number: ");
                    int itemNo = sc.nextInt();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    cart.addOrder(things[itemNo - 1], qty, things, cost);
                    break;

                case 2:
                    cart.getOrder();
                    break;

                case 3:
                    System.out.print("Enter item name to remove: ");
                    String removeItem = sc.nextLine();
                    cart.removeOrder(removeItem, things, cost);
                    break;

                case 4:
                    cart.emptyCart();
                    break;

                case 5:
                    cart.getOrder();
                    System.out.println("Total Bill: Rs." + cart.getTotal());
                    break;

                case 6:
                    cart.saveCartToFile();
                    break;

                case 7:
                    System.out.println("THANK YOU FOR ShoppingING..");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
