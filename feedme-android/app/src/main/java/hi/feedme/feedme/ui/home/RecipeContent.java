package hi.feedme.feedme.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hi.feedme.feedme.models.SimplifiedRecipe;

/**
 * Simple container class for static HomeFragment recipes
 */
public class RecipeContent {
    public static List<SimplifiedRecipe> items = new ArrayList<SimplifiedRecipe>();
    public static Map<Integer, SimplifiedRecipe> itemMap = new HashMap<Integer,SimplifiedRecipe>();
}