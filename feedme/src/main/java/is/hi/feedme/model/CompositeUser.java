package is.hi.feedme.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
        setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class CompositeUser {

    @JsonProperty("user")
    private SimplifiedUser user;

    @JsonProperty("recipes")
    private List<SimplifiedRecipe> recipes;

    public void setSimplifiedUser(SimplifiedUser user) {
        this.user = user;
    }

    public SimplifiedUser getSimplifiedUser() {
        return user;
    }

    public void setSimplifiedRecipes(List<SimplifiedRecipe> recipes) {
        this.recipes = recipes;
    }

    public List<SimplifiedRecipe> getSimplifiedRecipes() {
        return recipes;
    }

}
