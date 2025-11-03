package AIBasedRecommendtionSystem;

import java.util.*;

public class SimpleRecommendationSystem {

    // Sample data: users and their liked products
    static Map<String, List<String>> userPreferences = new HashMap<>();

    public static void main(String[] args) {
        // Step 1: Add user data
        userPreferences.put("User1", Arrays.asList("Laptop", "Mouse", "Keyboard"));
        userPreferences.put("User2", Arrays.asList("Mouse", "Keyboard", "Monitor"));
        userPreferences.put("User3", Arrays.asList("Laptop", "Headphones"));
        userPreferences.put("User4", Arrays.asList("Keyboard", "Monitor", "Webcam"));

        // Step 2: Choose the target user
        String targetUser = "User1";

        // Step 3: Generate recommendation
        List<String> recommendations = getRecommendations(targetUser);

        // Step 4: Show results
        System.out.println("User Preferences:");
        for (String user : userPreferences.keySet()) {
            System.out.println(user + " -> " + userPreferences.get(user));
        }

        System.out.println("\nRecommended products for " + targetUser + ": " + recommendations);
    }

    // Method to generate recommendations
    public static List<String> getRecommendations(String targetUser) {
        List<String> targetItems = userPreferences.get(targetUser);
        Set<String> recommended = new HashSet<>();

        for (Map.Entry<String, List<String>> entry : userPreferences.entrySet()) {
            String otherUser = entry.getKey();
            if (otherUser.equals(targetUser)) continue;

            List<String> otherItems = entry.getValue();

            // Find similar users (those who like at least one same product)
            for (String item : otherItems) {
                for (String tItem : targetItems) {
                    if (item.equalsIgnoreCase(tItem)) {
                        // Add all their liked items to recommendation list
                        recommended.addAll(otherItems);
                    }
                }
            }
        }

        // Remove items already liked by target user
        recommended.removeAll(targetItems);

        return new ArrayList<>(recommended);
    }
}