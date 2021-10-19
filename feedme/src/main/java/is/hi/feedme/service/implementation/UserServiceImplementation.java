package is.hi.feedme.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import is.hi.feedme.repository.ReviewRepository;
import is.hi.feedme.repository.UserRepository;
import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;
import is.hi.feedme.model.CompositeUser;
import is.hi.feedme.model.Recipe;
import is.hi.feedme.model.SimplifiedRecipe;
import is.hi.feedme.model.SimplifiedUser;
import is.hi.feedme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImplementation implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    private SimplifiedUser createSimpleUser(User user) {
        SimplifiedUser s = new SimplifiedUser();
        s.setId(user.getId());
        s.setUsername(user.getUsername());
        s.setEmail(user.getEmail());
        s.setAdmin(user.getAdmin());

        return s;
    }

    private SimplifiedRecipe createSimpleRecipe(Recipe curr) {
        long currId = curr.getId();

        SimplifiedRecipe s = new SimplifiedRecipe();
        s.setId(currId);
        s.setName(curr.getName());

        try {
            s.setRating(reviewRepository.averageRatingByRecipeId(currId));
        } catch (Exception e) {
            // No rating exists, apply 0.0
            s.setRating(0.0);
        }

        s.setDescription(curr.getDescription());
        s.setCalories(curr.getCalories());
        s.setProteins(curr.getProteins());
        s.setCarbs(curr.getCarbs());
        s.setFats(curr.getFats());
        s.setImage(curr.getImage());

        return s;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + (user.getAdmin() ? "ADMIN" : "USER")));

        return authorities;
    }

    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<SimplifiedUser> findAllSimpleUsers() {
        List<SimplifiedUser> list = new ArrayList<>();
        Iterator<User> userIterator = userRepository.findAll().iterator();

        while (userIterator.hasNext()) {
            list.add(createSimpleUser(userIterator.next()));
        }

        return list;
    }

    @Override
    public User findOneUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findOneUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public CompositeUser findCompositeUser(String name) {
        User u = findOneUser(name);
        CompositeUser cu = new CompositeUser();
        cu.setSimplifiedUser(createSimpleUser(u));

        Set<Recipe> recipes = u.getRecipes();
        List<SimplifiedRecipe> sr = new ArrayList<>();

        for(Recipe r : recipes) {
            sr.add(createSimpleRecipe(r));
        }

        cu.setSimplifiedRecipes(sr);
        return cu;
    }

    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        return userRepository.save(nUser);
    }
}