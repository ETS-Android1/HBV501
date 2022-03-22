package hi.feedme.feedme.ui.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hi.feedme.feedme.models.Review;

/**
 * Simple container class for static Recipe Reviews
 */
public class ReviewContent {
    public static List<Review> items = new ArrayList<Review>();
    public static Map<Integer, Review> itemMap = new HashMap<Integer, Review>();
}