package is.hi.feedme.repository;

import is.hi.feedme.model.Comment;
import is.hi.feedme.model.Recipe;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The main repository for handling comments
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findByRecipe(Recipe recipe);
}
