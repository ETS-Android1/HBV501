package is.hi.feedme.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Helper object to create a pagination wrapper for a list of SimplifiedRecipe Objects.
 * This is used for responses that are given to GET requests on /recipes
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE,
        setterVisibility=JsonAutoDetect.Visibility.NONE, creatorVisibility=JsonAutoDetect.Visibility.NONE)
public class CompositeRecipe {

    // The list of recipes constructed from the given querystring
    @JsonProperty("recipes")
    private List<SimplifiedRecipe> recipes;
    
    // Pagination href links
    @JsonProperty("_prev")
    private String previous;

    @JsonProperty("_curr")
    private String current;

    @JsonProperty("_next")
    private String next;

    // The number of total pagination pages
    private int pages;

    public void setSimplifiedRecipes(List<SimplifiedRecipe> recipes) {
        this.recipes = recipes;
    }

    public List<SimplifiedRecipe> getSimplifiedRecipes() {
        return recipes;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPrevious() {
        return previous;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCurrent() {
        return current;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getNext() {
        return next;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

}
