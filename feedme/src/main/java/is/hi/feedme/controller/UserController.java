package is.hi.feedme.controller;

import is.hi.feedme.config.TokenProvider;
import is.hi.feedme.model.AuthToken;
import is.hi.feedme.model.CompositeUser;
import is.hi.feedme.model.LoginUser;
import is.hi.feedme.model.SimplifiedUser;
import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;
import is.hi.feedme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 * HTTP routes for requests to /users
 * 
 * NOTE: In all cases password information is omitted from response objects
 * </pre>
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    /**
     * POST on /users/login, no authentication required
     *
     * @param loginUser the request body required for login (username, password)
     * @return the JWT bearer token to insert into future requests
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> validateUser(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    /**
     * POST on /users/register, no authentication required
     *
     * @param user the request body of the user creation request
     * @return the information of the created user ( password field omitted )
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    /**
     * GET on /users/me, any authentication required
     * 
     * @return Composite user information for the current user
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public CompositeUser findCurrentUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findCompositeUser(auth.getName());
    }

    /**
     * GET on /users, admin authentication required
     * 
     * @return a list of all simple user information
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<SimplifiedUser> findAllUsers() {
        return userService.findAllSimpleUsers();
    }

    /**
     * GET on /users/{id}, admin authentication required
     * 
     * @param id the id of the user to look up
     * @return the user info of the user
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

}