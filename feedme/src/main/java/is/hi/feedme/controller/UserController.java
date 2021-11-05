package is.hi.feedme.controller;

import is.hi.feedme.config.TokenProvider;
import is.hi.feedme.model.LoginResponse;
import is.hi.feedme.model.CompositeUser;
import is.hi.feedme.model.LoginUser;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.SimplifiedUser;
import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;
import is.hi.feedme.service.RecipeService;
import is.hi.feedme.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private RecipeService recipeService;

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

        SimplifiedUser user = userService.createSimpleUser(userService.findUserByUsername(loginUser.getUsername()));

        // CORS policy is set to 3600 so max token age uses that
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(user, token, 3600));
    }

    /**
     * POST on /users/register, no authentication required
     *
     * @param user the request body of the user creation request
     * @return the information of the created user ( password field omitted )
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        User nameCheck = null;
        User emailCheck = null;

        try {
            nameCheck = userService.findUserByUsername(user.getUsername());
        } catch (Exception e) {
            // Unused
        }

        if (nameCheck != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username");
        }

        try {
            emailCheck = userService.findUserByEmail(user.getEmail());
        } catch (Exception e) {
            // Unused
        }

        if (emailCheck != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email");
        }

        SimplifiedUser nUser = userService.createSimpleUser(userService.createUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(nUser);
    }

    /**
     * GET on /users/me, any authentication required
     * 
     * @return Composite user information for the current user
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public CompositeUser findCurrentUserInfo() {
        // This route doesn't need explicit error handling, 401 is handled automatically
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findCompositeUser(auth.getName());
    }

    /**
     * POST on /recipes/{id}/reviews, user authentication is required
     * 
     * @param id the id of the recipe to add
     * @return the status of the creation request
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "me/recipes/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> addUserRecipe(@PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Recipe r = null;

        try {
            r = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        Set<Recipe> userRecipes = user.getRecipes(); 


        if (userRecipes.contains(r)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("user already has this recipe stored");
        }

        userRecipes.add(r);
        user.setRecipes(userRecipes);
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * DELETE on /recipes/{id}/reviews, user authentication is required
     * 
     * @param id the id of the recipe to delete
     * @return the status of the creation request
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "me/recipes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserRecipe(@PathVariable long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Recipe r = null;

        try {
            r = recipeService.findRecipeById(id);
        } catch (Exception e) {
            // Unused
        }

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no recipe with that id exists");
        }

        Set<Recipe> userRecipes = user.getRecipes(); 


        if (!userRecipes.contains(r)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user does not have this recipe stored");
        }

        userRecipes.remove(r);
        user.setRecipes(userRecipes);
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * GET on /users, admin authentication required
     * 
     * @return a list of all simple user information
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<SimplifiedUser>> findAllUsers() {
        List<SimplifiedUser> su = null;

        try {
            su = userService.findAllSimpleUsers();
        } catch (Exception e) {
            // Unused
        }

        if (su == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(su);
    }

    /**
     * GET on /users/{id}, admin authentication required
     * 
     * @param id the id of the user to look up
     * @return the user info of the user
     */
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUserById(@PathVariable long id) {
        User u = null;
        
        try {
            u = userService.findUserById(id);
        } catch (Exception e) {
            // Unused
        }

        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no user with that id exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

}
