package is.hi.feedme.repository;

import is.hi.feedme.model.Review;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT * FROM reviews WHERE recipe_id = :recipeId AND user_id = :userId", nativeQuery = true)
    Review findByRecipeIdAndUserId(@Param("recipeId") long recipeId, @Param("userId") long userId);

    @Query("SELECT AVG(rating) FROM Review WHERE recipe_id=:recipe_id")
    double averageRatingByRecipeId(@Param("recipe_id") long recipe_id);

    @Query(value = "INSERT INTO reviews(recipe_id, user_id, title, subtitle, \"date\", rating) VALUES(:recipeId, :userId, :title, :subtitle, CURRENT_TIMESTAMP, :rating) "
            + "ON CONFLICT(recipe_id, user_id) DO UPDATE SET title = :title, subtitle = :subtitle, \"date\" = CURRENT_TIMESTAMP, rating = :rating", nativeQuery = true)
    @Modifying
    @Transactional
    int saveRecipeReview(@Param("recipeId") long recipeId, @Param("userId") long userId, @Param("title") String title,
            @Param("subtitle") String subtitle, @Param("rating") int rating);

    @Query(value = "DELETE FROM reviews WHERE recipe_id = :recipeId AND user_id = :userId", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteRecipeReview(@Param("recipeId") long recipeId, @Param("userId") long userId);

}