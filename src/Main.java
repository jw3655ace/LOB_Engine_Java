import java.math.BigDecimal;

public class Main {
    public static void main(String[] args){
        OrderBook book = new OrderBook();

        // SCENARIO 1: THE BUY BOOK (MAX-HEAP)
        // Adding a low price and then a high price to see if the higher price moves up
        book.addOrder(new Order(1, new BigDecimal("100.00"), 10, true));
        book.addOrder(new Order(1, new BigDecimal("150.00"), 5, true));
        book.addOrder(new Order(3, new BigDecimal("120.00"), 20, true));

        // SCENARIO 2: THE SELL BOOK (MIN-HEAP)
        // Adding a high price and then a low one to see if the low price levels up
        book.addOrder(new Order(4, new BigDecimal("200.00"), 10, false));
        book.addOrder(new Order(5, new BigDecimal("105.00"), 5, false));

        // TESTING PERFORMANCE
        System.out.println("--- TESTING ORDERBOOK LOGIC ---");
        System.out.println("Expected Best Bid: 150.00 | Actual: " + book.getBestBid());
        System.out.println("Expected Best Ask: 105.00 | Actual: " + book.getBestAsk());

        // FINAL STATE CHECK
        book.printMarketState();

    }
}
