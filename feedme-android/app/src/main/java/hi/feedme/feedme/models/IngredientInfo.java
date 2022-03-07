package hi.feedme.feedme.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = IngredientInfoDeserializer.class)
public class IngredientInfo {
    private int id;
    private String name;

    public IngredientInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
