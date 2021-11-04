package is.hi.feedme.repository;

import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The main repository for handling user data
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(long id);

    User save(UserDto user);
    
    void delete(User user);

}