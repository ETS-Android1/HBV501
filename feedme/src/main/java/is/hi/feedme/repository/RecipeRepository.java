package is.hi.feedme.repository;

import is.hi.feedme.model.Recipe;

import java.util.List;

import org.springframework.data.domain.Sort;
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
    List<Recipe> findByIds(@Param("ids") List<Long> recipeIdsList, Sort sort);

    @Query(value = "SELECT * FROM recipes ORDER BY :sort", nativeQuery = true)
    List<Recipe> findAllSortedBy(@Param("sort") String sort);

    @Query(value = "SELECT * FROM recipes ORDER BY :sort LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Recipe> findAllSortedByPaginated(@Param("sort") String sort, @Param("limit") int limit, @Param("offset") int offset);

    @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM (SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) ORDER BY :sort", nativeQuery = true)
    List<Recipe> findByIngredientIds(@Param("sort") String sort, @Param("ids") List<Long> recipeIdsList, @Param("size") int size);

    @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM (SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) ORDER BY :sort LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Recipe> findByIngredientIdsPaginated(@Param("sort") String sort, @Param("ids") List<Long> recipeIdsList, @Param("size") int size,
            @Param("limit") int limit, @Param("offset") int offset);

}