package is.hi.feedme.service;

import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}