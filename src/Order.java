import java.math.BigDecimal; // You need this for financial precision
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final long id;
    private final BigDecimal price;
    private final int quantity;
    private final boolean isBuy; // True for Buy orders, False for Sell orders
    private final long timestamp; // When the order was placed

    public Order(long id, BigDecimal price, int quantity, boolean isBuy){
        // Validation: Where I prevent the system from crashing
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price must be > 0");
        }
        if (quantity <= 0){
            throw new IllegalArgumentException("quantity must be > 0");
        }

        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.isBuy = isBuy;
        this.timestamp = System.nanoTime(); // Records time to the very nanosecond. I understand that in HFT, two orders might arrive in the same millisecond. I am using nanosecond precision to ensure the first person gets the trade.
    }

    public boolean isBuy(){
        return isBuy;
    }

    public java.math.BigDecimal getPrice(){
        return price;
    }


}
