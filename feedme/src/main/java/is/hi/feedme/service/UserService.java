package is.hi.feedme.service;

import is.hi.feedme.model.SimplifiedUser;
import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);

    List<User> findAllUsers();

    List<SimplifiedUser> findAllSimpleUsers();

    User findOneUser(String username);

    User findOneUser(long id);
}