package hi.feedme.feedme.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = IngredientDeserializer.class)
public class Ingredient {
    private IngredientInfo ingredient;
    private double quantity;
    private String unit;

    public Ingredient(IngredientInfo ingredient, double quantity, String unit) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public IngredientInfo getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientInfo ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
