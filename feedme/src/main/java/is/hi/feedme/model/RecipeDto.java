package is.hi.feedme.model;

public class RecipeDto {

    private String name;
    private String instructions;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Recipe getRecipeFromDto() {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setInstructions(instructions);
        recipe.setImage(image);

        return recipe;
    }
}