package is.hi.feedme.repository;

import is.hi.feedme.model.Recipe;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * The main repository for recipes
 * 
 * This has a bunch of native PostgreSQL queries to handle the format of the
 * /recipes querystring.
 * 
 * Many of these may look like duplicates but Jpa repositories do not provide
 * dynamic sorting for native queries. Additionally information about the database, 
 * such as column names, is blocked from being passed as an @param. As a result of this
 * every sorting method we want requires a duplicate query with a different order by...
 * </pre>
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
        Recipe findByName(String name);

        Recipe findById(long id);

        void delete(Recipe recipe);

        @Query("SELECT r FROM Recipe r WHERE r.id IN :ids")
        List<Recipe> findByIds(@Param("ids") List<Long> recipeIdsList, Sort sort);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY id ASC", nativeQuery = true)
        List<Recipe> findAllSortedById(@Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY id ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findAllSortedByIdPaginated(@Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY id ASC", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedById(@Param("ids") List<Long> recipeIdsList, @Param("size") int size,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY id ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByIdPaginated(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY calories ASC", nativeQuery = true)
        List<Recipe> findAllSortedByCalories(@Param("minCalories") int minCalories,
                        @Param("maxCalories") int maxCalories, @Param("minCarbs") int minCarbs,
                        @Param("maxCarbs") int maxCarbs, @Param("minProteins") int minProteins,
                        @Param("maxProteins") int maxProteins, @Param("minFats") int minFats,
                        @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY calories ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findAllSortedByCaloriesPaginated(@Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY calories ASC", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByCalories(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("minCalories") int minCalories,
                        @Param("maxCalories") int maxCalories, @Param("minCarbs") int minCarbs,
                        @Param("maxCarbs") int maxCarbs, @Param("minProteins") int minProteins,
                        @Param("maxProteins") int maxProteins, @Param("minFats") int minFats,
                        @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY calories ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByCaloriesPaginated(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY carbs ASC", nativeQuery = true)
        List<Recipe> findAllSortedByCarbs(@Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY carbs ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findAllSortedByCarbsPaginated(@Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY carbs ASC", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByCarbs(@Param("ids") List<Long> recipeIdsList, @Param("size") int size,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY carbs ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByCarbsPaginated(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY proteins ASC", nativeQuery = true)
        List<Recipe> findAllSortedByProteins(@Param("minCalories") int minCalories,
                        @Param("maxCalories") int maxCalories, @Param("minCarbs") int minCarbs,
                        @Param("maxCarbs") int maxCarbs, @Param("minProteins") int minProteins,
                        @Param("maxProteins") int maxProteins, @Param("minFats") int minFats,
                        @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY proteins ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findAllSortedByProteinsPaginated(@Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY proteins ASC", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByProteins(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("minCalories") int minCalories,
                        @Param("maxCalories") int maxCalories, @Param("minCarbs") int minCarbs,
                        @Param("maxCarbs") int maxCarbs, @Param("minProteins") int minProteins,
                        @Param("maxProteins") int maxProteins, @Param("minFats") int minFats,
                        @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY proteins ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByProteinsPaginated(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY fats ASC", nativeQuery = true)
        List<Recipe> findAllSortedByFats(@Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE " + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY fats ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findAllSortedByFatsPaginated(@Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)" + "ORDER BY fats ASC", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByFats(@Param("ids") List<Long> recipeIdsList, @Param("size") int size,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) t) AND"
                        + "(calories > :minCalories AND calories < :maxCalories) AND "
                        + "(carbs > :minCarbs AND carbs < :maxCarbs) AND "
                        + "(proteins > :minProteins AND proteins < :maxProteins) AND"
                        + "(fats > :minFats AND fats < :maxFats)"
                        + "ORDER BY fats ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
        List<Recipe> findByIngredientIdsSortedByFatsPaginated(@Param("ids") List<Long> recipeIdsList,
                        @Param("size") int size, @Param("limit") int limit, @Param("offset") int offset,
                        @Param("minCalories") int minCalories, @Param("maxCalories") int maxCalories,
                        @Param("minCarbs") int minCarbs, @Param("maxCarbs") int maxCarbs,
                        @Param("minProteins") int minProteins, @Param("maxProteins") int maxProteins,
                        @Param("minFats") int minFats, @Param("maxFats") int maxFats);

        @Query(value = "SELECT COUNT(*) FROM recipes", nativeQuery = true)
        int findCount();

        @Query(value = "SELECT COUNT(*) FROM (SELECT * FROM recipes WHERE id IN (SELECT recipe_id FROM "
                        + "(SELECT recipe_id, COUNT(ingredient_id) FROM ingredient_quantity WHERE ingredient_id IN :ids "
                        + "GROUP BY recipe_id HAVING COUNT(ingredient_id) >= :size) a)) b", nativeQuery = true)
        int findCountFiltered(@Param("ids") List<Long> recipeIdsList, @Param("size") int size);
}