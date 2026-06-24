package Week1.DSA;

class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=$" + totalPrice +
                '}';
    }
}

public class SortingOrders {

    // 1. Bubble Sort (Ascending by totalPrice)
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // 2. Quick Sort (Ascending by totalPrice)
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i+1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    // Utility helper to copy array for clean test comparisons
    private static Order[] copyArray(Order[] source) {
        Order[] copy = new Order[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }

    public static void main(String[] args) {
        Order[] originalOrders = {
            new Order("O001", "Alice", 250.75),
            new Order("O002", "Bob", 99.99),
            new Order("O003", "Charlie", 1500.00),
            new Order("O004", "Diana", 450.50),
            new Order("O005", "Ethan", 75.25),
            new Order("O006", "Fiona", 450.50)  // Same price to demonstrate stability
        };

        System.out.println("=== Sorting Customer Orders Demo ===");
        
        System.out.println("\nOriginal Orders:");
        for (Order o : originalOrders) {
            System.out.println("  " + o);
        }

        // 1. Bubble Sort Demo
        Order[] bubbleSorted = copyArray(originalOrders);
        bubbleSort(bubbleSorted);
        System.out.println("\nOrders Sorted with Bubble Sort:");
        for (Order o : bubbleSorted) {
            System.out.println("  " + o);
        }

        // 2. Quick Sort Demo
        Order[] quickSorted = copyArray(originalOrders);
        quickSort(quickSorted, 0, quickSorted.length - 1);
        System.out.println("\nOrders Sorted with Quick Sort:");
        for (Order o : quickSorted) {
            System.out.println("  " + o);
        }

        // Analysis
        System.out.println("\n=== Complexity Analysis and Comparison ===");
        System.out.println("1. Bubble Sort:");
        System.out.println("   - Time Complexity: Best-case O(N) [with optimized check, but here standard O(N^2)], Average-case O(N^2), Worst-case O(N^2).");
        System.out.println("   - Space Complexity: O(1) auxiliary space (In-place).");
        System.out.println("   - Stability: Stable. Keeps relative order of equal elements (e.g. Diana O004 and Fiona O006).");
        
        System.out.println("2. Quick Sort:");
        System.out.println("   - Time Complexity: Best-case O(N log N), Average-case O(N log N), Worst-case O(N^2) (occurs if array is already sorted and bad pivot is chosen).");
        System.out.println("   - Space Complexity: O(log N) due to recursive call stack.");
        System.out.println("   - Stability: Unstable. Might swap relative positions of equal elements.");
        
        System.out.println("\nWhy Quick Sort is Preferred in Practice:");
        System.out.println("- Efficiency: Quick Sort averages O(N log N) time complexity which performs orders of magnitude faster than O(N^2) algorithms like Bubble Sort for large datasets.");
        System.out.println("- Cache Friendliness: It exhibits good locality of reference, making it extremely fast on modern computer architectures.");
        System.out.println("- In-place: It uses minimal auxiliary space compared to other O(N log N) algorithms like Merge Sort (which requires O(N) extra space).");
    }
}
