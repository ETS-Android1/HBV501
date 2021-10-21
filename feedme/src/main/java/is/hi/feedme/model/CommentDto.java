package is.hi.feedme.model;

/**
 * Helper Object for working with request bodies for comment insertion 
 */
public class CommentDto {

    private Long id;
    private String body;
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Comment getCommentFromDto() {
        Comment comment = new Comment();
        comment.setBody(body);

        return comment;
    }
}
