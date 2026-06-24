package Week1.DSA;

public class FinancialForecasting {

    // 1. Recursive Method to calculate future value
    // Formula: V(n) = V(n-1) * (1 + growthRate)
    // Base Case: V(0) = presentValue
    public static double calculateFutureValueRecursive(double presentValue, double growthRate, int periods) {
        // Base Case
        if (periods == 0) {
            return presentValue;
        }
        // Recursive Step
        return calculateFutureValueRecursive(presentValue, growthRate, periods - 1) * (1 + growthRate);
    }

    // 2. Optimized Iterative Method to calculate future value (avoids call stack overhead)
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int periods) {
        double futureValue = presentValue;
        double rateMultiplier = 1 + growthRate;
        for (int i = 0; i < periods; i++) {
            futureValue *= rateMultiplier;
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double principal = 1000.0; // Initial investment
        double growthRate = 0.05; // 5% annual growth rate
        int periods = 10;        // 10 years

        System.out.println("=== Financial Forecasting Demo ===");
        System.out.println("Initial Value: $" + principal);
        System.out.println("Annual Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Forecast Period: " + periods + " periods (years)");

        // 1. Test Recursive calculation
        double recursiveResult = calculateFutureValueRecursive(principal, growthRate, periods);
        System.out.printf("\nFuture Value (Recursive): $%.2f\n", recursiveResult);

        // 2. Test Iterative calculation
        double iterativeResult = calculateFutureValueIterative(principal, growthRate, periods);
        System.out.printf("Future Value (Iterative): $%.2f\n", iterativeResult);

        // Discussion on large periods to show stack risk
        int largePeriods = 10000;
        System.out.println("\nTesting with large periods (" + largePeriods + "):");
        System.out.println("Iterative calculation runs smoothly...");
        double largeIterativeResult = calculateFutureValueIterative(principal, growthRate, largePeriods);
        System.out.printf("  Future Value (Iterative): $%.2f\n", largeIterativeResult);

        System.out.println("Note: Calling calculateFutureValueRecursive with " + largePeriods + 
                           " periods could cause a StackOverflowError in environments with limited stack sizes due to deep recursion.");

        // Analysis
        System.out.println("\n=== Complexity Analysis and Discussion ===");
        System.out.println("1. Recursive Algorithm:");
        System.out.println("   - Time Complexity: O(N) where N is the number of periods. Requires N recursive calls.");
        System.out.println("   - Space Complexity: O(N) auxiliary space. Each recursive call adds a stack frame to the system call stack.");
        System.out.println("   - Recursive Formula: V(n) = V(n-1) * (1 + r) with base case V(0) = V_present.");
        
        System.out.println("2. Iterative Algorithm (Optimized):");
        System.out.println("   - Time Complexity: O(N) where N is the number of periods. Loop runs N times.");
        System.out.println("   - Space Complexity: O(1) auxiliary space. Only uses a few local variables for computation.");

        System.out.println("\n=== Stack Overhead and Recursion Risks ===");
        System.out.println("- Stack Overflow: Each recursive invocation consumes stack space. If 'periods' (N) is large, the call stack exceeds the thread's memory limit, throwing a StackOverflowError.");
        System.out.println("- Optimization: Converting the recursion to a simple iterative loop (or using memoization for complex tree recursion) reduces the auxiliary space complexity from O(N) to O(1), completely removing stack overflow risks.");
    }
}
