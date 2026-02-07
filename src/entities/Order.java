package entities;

import entities.enums.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private LocalDateTime moment;
    private OrderStatus status;

    private Client client;
    private List<OrderItem> items = new ArrayList<>();
    public static final DateTimeFormatter ORDER_FMT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Order() {
    }

    public Order(OrderStatus status, Client client) {
        this.moment = LocalDateTime.now();
        this.status = status;
        this.client = client;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public Double total(){
        Double sum = 0.0;
        
        for (OrderItem item : items){
            sum += item.subTotal();
        }
        
        return sum;
    }
    
    public LocalDateTime getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ORDER SUMMARY:").append(System.lineSeparator());
        sb.append("Order moment: ").append(moment.format(ORDER_FMT)).append(System.lineSeparator());
        sb.append("Order status: ").append(status).append(System.lineSeparator());
        sb.append(String.format("Client: %s (%s) - %s",
                client.getName(),
                client.getBirthDate().format(Client.BIRTH_FMT),
                client.getEmail())).append(System.lineSeparator());
        sb.append("Order items:").append(System.lineSeparator());

        for (OrderItem item : items) {
            sb.append(String.format("%s, $%.2f, Quantity: %d, Subtotal: $%.2f",
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getQuantity(),
                    item.subTotal()));
            sb.append(System.lineSeparator());
        }
        
        sb.append(String.format("Total price: $%.2f", total()));
        
        return sb.toString();
    }
}
