package is.hi.feedme.repository;

import is.hi.feedme.model.Ingredient;
import is.hi.feedme.model.IngredientDto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The main repository for handling ingredients
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // TODO: <List>Ingredient findByRecipe(Recipe recipe);

    Ingredient findByName(String name);

    Ingredient save(IngredientDto ingredient);

    void delete(Ingredient ingredient);

    @Query(value = "SELECT * FROM ingredients WHERE id IN (SELECT ingredient_ID FROM ingredient_quantity)", nativeQuery = true)
    List<Ingredient> findIngredientsUsedByRecipes();

}
