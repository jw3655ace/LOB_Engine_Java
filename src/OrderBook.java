import java.util.PriorityQueue;

public class OrderBook {
    // BUY Side: Highest price first (Max-Heap)
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>((a, b) -> b.getPrice().compareTo(a.getPrice()));

    // SELL Side: Lowest price first (Min-Heap)
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>((a, b) -> a.getPrice().compareTo(b.getPrice()));

    public void addOrder(Order order){
        // Route orders to the correct side of the book (buy or sell)
        if (order.isBuy()){
            buyOrders.offer(order);
        } else {
            sellOrders.offer(order);
        }
    }
}


