# Java HFT Matching Engine (Core Shell)

A low-latency limit order book implementation focusing on price-time priority and deterministic execution.

## 🚀 Technical Highlights
- **Architecture:** Dual-Heap (Binary Heap) design utilizing `PriorityQueues`.
- **Performance:** Optimized for **O(1) best-price retrieval** and **O(log n) order placement**.
- **Data Integrity:** Implemented `BigDecimal` for all price-point calculations to eliminate **floating-point errors** and ensure financial accuracy.
- **Priority Logic:** Strict **Price-Time Priority** matching algorithm.

## 🛠 Project Status: Initial Successful Run
This project is currently in the **Proof-of-Concept** phase. I have successfully achieved:
1. **Bid/Ask Separation:** Maintaining separate Max-Heaps for Bids and Min-Heaps for Asks.
2. **Deterministic Matching:** The engine correctly identifies matches when the Bid price >= the Ask price.
3. **Core Validation:** Verified the engine's ability to maintain order integrity during high-volume spikes.

## 📈 Future Roadmap
- Transition from `BigDecimal` to **Fixed-Point Math** using `long` to minimize Garbage Collection latency.
- Implement **O(1) Order Tracking** via HashMaps for faster cancellations and modifications.
- Build a TCP/UDP gateway for real-time market data simulation.

---
**Developed by Jonathan Wang**  
*Davidson College CS Student | Focus: Low-Latency Systems*
