package is.hi.feedme.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import is.hi.feedme.repository.UserRepository;
import is.hi.feedme.model.User;
import is.hi.feedme.model.UserDto;
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
    private BCryptPasswordEncoder bcryptEncoder;

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

        while(userIterator.hasNext()) {
            User curr = userIterator.next();

            SimplifiedUser s = new SimplifiedUser();
            s.setId(curr.getId());
            s.setUsername(curr.getUsername());
            s.setEmail(curr.getEmail());
            s.setAdmin(curr.getAdmin());

            list.add(s);
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
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        return userRepository.save(nUser);
    }
}