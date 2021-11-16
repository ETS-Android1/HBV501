package is.hi.feedme.model;

public class IngredientQuantityDto {

    long ingredient;
    double quantity;
    String unit;

    public long getIngredient() {
        return ingredient;
    }

    public void setIngredient(long id) {
        this.ingredient = id;
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
