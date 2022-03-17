package hi.feedme.feedme.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hi.feedme.feedme.models.SimplifiedRecipe;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class RecipeContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<PlaceholderItem> ITEMSold = new ArrayList<PlaceholderItem>(); //breyta i simprecipe
    public static List<SimplifiedRecipe> ITEMS = new ArrayList<SimplifiedRecipe>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAPold = new HashMap<String, PlaceholderItem>(); //breyta i simprecipe
    public static final Map<Integer, SimplifiedRecipe> ITEM_MAP = new HashMap<Integer,SimplifiedRecipe>();

    private static final int COUNT = 25;

    /*static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }*/

    /*private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }*/

    private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static SimplifiedItem createSimplifiedItem(SimplifiedRecipe rec) {
        return new SimplifiedItem(rec.getId(),rec.getName(),rec.getRating(),rec.getDescription(),rec.getCalories(),rec.getCarbs(),rec.getProteins(),rec.getFats()); // lesa shit fra vb
    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;
        public final String content;
        public final String details;

        public PlaceholderItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
    public static class SimplifiedItem {
        public final int id;
        public final String name;
        public final double rating;
        public final String description;
        public final double calories;
        public final double carbs;
        public final double proteins;
        public final double fats;

        public SimplifiedItem(int id, String name, double rating, String description, double calories, double carbs, double proteins, double fats) {
            this.id = id;
            this.name = name;
            this.rating = rating;
            this.description = description;
            this.calories = calories;
            this.carbs = carbs;
            this.proteins = proteins;
            this.fats = fats;
        }
        @Override
        public String toString() {return name + rating + calories + carbs + proteins + fats;}

    }
}