import java.util.PriorityQueue;
import java.math.BigDecimal;

public class OrderBook {
    // BUY Side: Highest price first (Max-Heap)
    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>((a, b) -> b.getPrice().compareTo(a.getPrice()));

    // SELL Side: Lowest price first (Min-Heap)
    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>((a, b) -> a.getPrice().compareTo(b.getPrice()));

    public BigDecimal getBestBid(){
        // If empty, returns ZERO, otherwise retrieve top order's price
        return buyOrders.isEmpty() ? BigDecimal.ZERO : buyOrders.peek().getPrice();
    }

    public BigDecimal getBestAsk(){
        return sellOrders.isEmpty() ? BigDecimal.ZERO : sellOrders.peek().getPrice();
    }

    public void addOrder(Order order){
        // Route orders to the correct side of the book (buy or sell)
        if (order.isBuy()){
            buyOrders.offer(order);
        } else {
            sellOrders.offer(order);
        }
    }

    public void printMarketState(){
        System.out.println("----- Market Snapshot -----");
        System.out.println("Best Bid: " + getBestBid());
        System.out.println("Best Ask: " + getBestAsk());
        System.out.println("---------------------------");
    }
}


