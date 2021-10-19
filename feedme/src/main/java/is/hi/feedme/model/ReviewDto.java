package is.hi.feedme.model;

public class ReviewDto {

    private int rating;
    private Recipe recipe;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Review getReviewFromDto() {
        Review review = new Review();
        review.setRating(rating);

        return review;
    }
}
