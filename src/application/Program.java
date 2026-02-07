package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter client data:");
        System.out.print("Name: ");
        String clientName = sc.nextLine();
        System.out.print("Email: ");
        String clientEmail = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        String clientBirthDate = sc.next();
        System.out.println("Enter order data:");
        System.out.print("Status: ");
        String orderStatus = sc.next();
        
        Order order = new Order(OrderStatus.valueOf(orderStatus.toUpperCase()), 
                new Client(clientName, clientEmail, LocalDate.parse(clientBirthDate, Client.BIRTH_FMT)));
        
        System.out.print("How many items to this order? ");
        int itemsQuantity = sc.nextInt();
        
        for (int i = 1; i <= itemsQuantity; i++) {
            sc.nextLine();
            
            System.out.printf("Enter #%d item data:\n", i);
            System.out.print("Product name: ");
            String productName = sc.nextLine();
            System.out.print("Product price: ");
            double productPrice = sc.nextDouble();
            System.out.print("Quantity: ");
            int productQuantity = sc.nextInt();
            
            order.addItem(new OrderItem(productQuantity, productPrice, 
                    new Product(productName, productPrice)));
        }
        
        System.out.println();
        System.out.println(order);
        
        sc.close();
    }
    
}
