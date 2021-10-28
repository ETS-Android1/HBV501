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

    /**
     * Standard function to save a user obtained from a request body. It is worth
     * keeping in mind that any newly registered users are automatically made into
     * normal users and not admins. If authorization is to be escalated it has to be
     * modified by a current admin.
     * 
     * @param user the UserDto made from the request body information
     * @return the User entity created from saving it
     */
    User createUser(UserDto user);

    /**
     * Standard function to delete a user based on its entity.
     * 
     * @param user the User entity to delete
     */
    void deleteUser(User user);

    /**
     * Basic find all service, returns a list of all users ordered by their IDs
     */
    List<User> findAllUsers();

    /**
     * A modification of the basic find all service, returns a list of all users in
     * a simplified format
     */
    List<SimplifiedUser> findAllSimpleUsers();

    /**
     * Basic find one service, returns the user with the given username
     * 
     * @param username the username to look for
     * @return the user entity associated with that username
     */
    User findUserByUsername(String username);

    /**
     * Basic find one service, returns the user with the given ID
     * 
     * @param id the ID of the user to look for
     * @return the user entity associated with that ID
     */
    User findUserById(long id);

    /**
     * Function to create a composite user view.
     * 
     * Stores basic user info as well as all recipes the user has chosen to store,
     * in a simplified format.
     */
    CompositeUser findCompositeUser(String username);

}