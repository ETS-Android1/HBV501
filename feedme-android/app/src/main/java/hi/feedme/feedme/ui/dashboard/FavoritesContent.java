package hi.feedme.feedme.ui.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hi.feedme.feedme.models.SimplifiedRecipe;

/**
 * Simple container class for static favorite recipes
 */
public class FavoritesContent {
    public static List<SimplifiedRecipe> items = new ArrayList<>();
    public static Map<Integer, SimplifiedRecipe> itemMap = new HashMap<>();
}