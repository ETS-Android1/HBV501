package is.hi.feedme.service;

import is.hi.feedme.model.CompositeUser;
import is.hi.feedme.model.SimplifiedUser;
import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;

import java.util.List;

/**
 * Interface definitions for the UserService.
 * 
 * Details for the individual implementation can be seen in the /implementation
 * directory.
 */
public interface UserService {
    User createUser(UserDto user);

    void deleteUser(User user);

    List<User> findAllUsers();

    List<SimplifiedUser> findAllSimpleUsers();

    User findUserByUsername(String username);

    User findUserById(long id);

    CompositeUser findCompositeUser(String username);
}