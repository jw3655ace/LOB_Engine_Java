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

    public void matchOrder(Order incomingOrder) {
        // Determine the target side (Buy matches with Sell/Ask)
        PriorityQueue<Order> oppositeSide = incomingOrder.isBuy() ? sellOrders : buyOrders;

        // The Matching Loop (Continue while there are orders and the price crosses)
        while (!oppositeSide.isEmpty() && incomingOrder.quantity() > 0) {
            Order topOrder = oppositeSide.peek();

            // Price Check
            int priceComparison = incomingOrder.getPrice().compareTo(topOrder.getPrice());
            boolean canMatch = incomingOrder.isBuy() ? (priceComparison >= 0) : (priceComparison <= 0);

            if (!canMatch) break; // Price no longer matches, stop!

            // Quantity Logic (Partial Fills)
            int matchQty = Math.min(incomingOrder.quantity(), topOrder.quantity());

            // Update quantities [Meticulous State Management]
            // To do soon - implement a 'reduceQuantity' method in Order.java
            incomingOrder.reduceQuantity(matchQty);
            topOrder.reduceQuantity(matchQty);

            // Clean up those "Piles"
            if (topOrder.quantity() == 0) {
                oppositeSide.poll(); // Remove fully filled order
            }

            System.out.println("TRADE EXECUTED: " + matchQty + " shares at " + topOrder.getPrice());
        }

        // If incoming order still has shares, add it to its own book
        if (incomingOrder.quantity() > 0) {
            if (incomingOrder.isBuy()) buyOrders.add(incomingOrder);
            else sellOrders.add(incomingOrder);
        }
    }

    public void printMarketState(){
        System.out.println("----- Market Snapshot -----");
        System.out.println("Best Bid: " + getBestBid());
        System.out.println("Best Ask: " + getBestAsk());
        System.out.println("---------------------------");
    }
}


