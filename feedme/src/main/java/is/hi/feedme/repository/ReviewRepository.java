package is.hi.feedme.repository;

import is.hi.feedme.model.Review;
import is.hi.feedme.model.ReviewDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * The main repository for handling reviews
 * 
 * The non standard entity queries are used for building composite routes.
 * An example of this would be displaying the average rating in the main
 * /recipes route. The average rating is not really a property of the recipe itself
 * it merely knows the list of reviews it has attached to it.
 * </pre>
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(ReviewDto review);

    void delete(Review review);

    @Query("SELECT AVG(rating) FROM Review WHERE recipe_id=:recipe_id")
    double averageRatingByRecipeId(@Param("recipe_id") long recipe_id);

}