package is.hi.feedme.repository;

import is.hi.feedme.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The main repository for handling comments
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    void delete(Comment comment);

}
