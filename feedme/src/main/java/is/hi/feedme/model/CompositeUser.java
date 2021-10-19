package is.hi.feedme.model;

import java.util.List;

public class CompositeUser {

    private SimplifiedUser user;
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
