package Week1.DSA;

import java.util.Arrays;
import java.util.Comparator;

class SearchProduct {
    private String productId;
    private String productName;
    private String category;

    public SearchProduct(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

public class EcommerceSearch {

    // 1. Linear Search
    public static SearchProduct linearSearch(SearchProduct[] products, String targetId) {
        for (SearchProduct product : products) {
            if (product.getProductId().equals(targetId)) {
                return product;
            }
        }
        return null;
    }

    // 2. Binary Search (Assumes array is sorted by productId)
    public static SearchProduct binarySearch(SearchProduct[] products, String targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = products[mid].getProductId().compareTo(targetId);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Setup initial product array
        SearchProduct[] products = {
            new SearchProduct("P105", "Wireless Keyboard", "Electronics"),
            new SearchProduct("P102", "USB-C Cable", "Accessories"),
            new SearchProduct("P108", "Coffee Mug", "Kitchenware"),
            new SearchProduct("P101", "Gaming Monitor", "Electronics"),
            new SearchProduct("P104", "Desk Organizer", "Office"),
            new SearchProduct("P103", "Ergonomic Chair", "Furniture"),
            new SearchProduct("P107", "Bluetooth Speaker", "Electronics"),
            new SearchProduct("P106", "Leather Wallet", "Accessories")
        };

        System.out.println("=== E-commerce Search Function Demo ===");

        // Test Linear Search
        String targetId = "P104";
        System.out.println("\n--- Testing Linear Search ---");
        System.out.println("Searching for product: " + targetId);
        SearchProduct result1 = linearSearch(products, targetId);
        System.out.println("Result: " + (result1 != null ? result1 : "Not Found"));

        // Test Binary Search
        System.out.println("\n--- Testing Binary Search ---");
        System.out.println("Sorting products by Product ID first...");
        // Sort products array by productId
        Arrays.sort(products, Comparator.comparing(SearchProduct::getProductId));
        
        System.out.println("Sorted Products:");
        for (SearchProduct p : products) {
            System.out.println("  " + p);
        }
        
        System.out.println("Searching for product: " + targetId);
        SearchProduct result2 = binarySearch(products, targetId);
        System.out.println("Result: " + (result2 != null ? result2 : "Not Found"));

        // Compare Search Performance (Benchmarking)
        System.out.println("\n--- Performance comparison ---");
        // Create a larger array to demonstrate difference
        int size = 10000;
        SearchProduct[] largeProductList = new SearchProduct[size];
        for (int i = 0; i < size; i++) {
            // Generate padded product IDs: P0000, P0001, etc.
            String id = String.format("P%04d", i);
            largeProductList[i] = new SearchProduct(id, "Product " + i, "Category");
        }
        // It is already sorted by productId because i increases sequentially.
        
        String searchTarget = "P9999"; // worst-case/last item
        
        // Measure linear search
        long startTime = System.nanoTime();
        linearSearch(largeProductList, searchTarget);
        long endTime = System.nanoTime();
        long linearDuration = endTime - startTime;
        System.out.println("Linear Search on " + size + " items took: " + linearDuration + " ns");

        // Measure binary search
        startTime = System.nanoTime();
        binarySearch(largeProductList, searchTarget);
        endTime = System.nanoTime();
        long binaryDuration = endTime - startTime;
        System.out.println("Binary Search on " + size + " items took: " + binaryDuration + " ns");

        // Analysis
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("1. Linear Search:");
        System.out.println("   - Time Complexity: O(N) worst-case, O(1) best-case. Average-case: O(N/2) = O(N).");
        System.out.println("   - Space Complexity: O(1) auxiliary space.");
        System.out.println("   - Advantage: Simple, does not require array to be sorted.");
        System.out.println("2. Binary Search:");
        System.out.println("   - Time Complexity: O(log N) worst/average-case, O(1) best-case.");
        System.out.println("   - Space Complexity: O(1) auxiliary space.");
        System.out.println("   - Requirement: Array must be sorted by the search key first. Sorting takes O(N log N) time.");
        System.out.println("\nConclusion: For static or rarely changing lists that are searched frequently, sorting once and performing binary search is significantly faster. For small or highly dynamic lists, linear search may be sufficient to avoid the sorting overhead.");
    }
}
