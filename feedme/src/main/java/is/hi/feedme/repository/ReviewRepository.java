package is.hi.feedme.repository;

import is.hi.feedme.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(rating) FROM Review WHERE recipe_id=:recipe_id")
    double averageRatingByRecipeId(@Param("recipe_id") long recipe_id);

}