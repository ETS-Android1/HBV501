package is.hi.feedme.repository;

import is.hi.feedme.model.Review;
import is.hi.feedme.model.Recipe;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Set<Review> findByRecipe(Recipe recipe);
}