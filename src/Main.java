import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Q1: Implement the code for the inline quick sort algorithm and sort the following data
        int[] arr = {2, 8, 7, 1, 3, 5, 6, 4};
        System.out.println("Q1: Original array: " + java.util.Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr));

        // Q2: Implement Adjacency list method for graph representation
        // as pre question we have 4 vertices connected to each other
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.printGraph();

        // Q3: Implement the coin-changing problem for Pakistani currency
        int[] denominations = {1, 2, 5, 10, 20, 50, 100, 500, 1000, 5000};
        int amount = 1988;
        coinChange(denominations, amount);

        // Q4: Implement the dynamic programming algorithm for 01 Knapsack problem
        int n = 4;
        int W = 5;
        int[] weights = {2, 3, 4, 5};
        int[] benefits = {3, 4, 5, 6};
        knapsack(n, W, weights, benefits);
    }

    // Q1: Quick sort logic
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Q2: Graph representation using adjacency list
    static class Graph {
        int V;
        ArrayList<ArrayList<Integer>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int src, int dest) {
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }

        void printGraph() {
            for (int i = 0; i < V; i++) {
                System.out.print("\nQ2: Vertex " + i + " is connected to: ");
                for (Integer vertex : adjList.get(i)) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
            }
        }
    }

    // Q3: Coin changing problem code
    private static void coinChange(int[] denominations, int amount) {
        System.out.println("\nQ3:Denominations for amount " + amount + ":");
        for (int i = denominations.length - 1; i >= 0; i--) {
            int count = amount / denominations[i];
            if (count > 0) {
                System.out.println(denominations[i] + " x " + count);
                amount -= denominations[i] * count;
            }
        }
    }

    // Q4: 01 Knapsack problem code
    private static void knapsack(int n, int W, int[] weights, int[] benefits) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    dp[i][w] = 0;
                else if (weights[i - 1] <= w)
                    dp[i][w] = Math.max(benefits[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        System.out.println("\nQ4: Dynamic Programming Table:");
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                System.out.print(dp[i][w] + " ");
            }
            System.out.println();
        }

        System.out.println("\nQ4: Items selected for maximum value:");
        int res = dp[n][W];
        int w = W;
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != dp[i - 1][w]) {
                System.out.println("Weight: " + weights[i - 1] + ", Benefit: " + benefits[i - 1]);
                res -= benefits[i - 1];
                w -= weights[i - 1];
            }
        }
    }
}
