package is.hi.feedme.repository;

import is.hi.feedme.model.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);

    Recipe findById(long id);

    void delete(Recipe recipe);
}