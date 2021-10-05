package is.hi.feedme.repository;

import is.hi.feedme.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByName(String name);
}