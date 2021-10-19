package is.hi.feedme.repository;

import is.hi.feedme.model.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByName(String name);

    Recipe findById(long id);

    void delete(Recipe recipe);

    @Query("SELECT r FROM Recipe r WHERE r.id IN :ids")
    List<Recipe> findByIds(@Param("ids") List<Long> recipeIdsList);

    // @Query("SELECT r from Recipe r where :size = (SELECT COUNT(ingredient_id) FROM r.ingredientQuantities WHERE ingredient_id in :ids)")
    // List<Recipe> findByIngredientIds(@Param("ids") List<Long> recipeIdsList, @Param("size") int size);

    @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM (SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN ?1 GROUP BY recipe_id HAVING COUNT(ingredient_id) >= ?2) t)", nativeQuery = true)
    List<Recipe> findByIngredientIds(@Param("ids") List<Long> recipeIdsList, @Param("size") int size);

}