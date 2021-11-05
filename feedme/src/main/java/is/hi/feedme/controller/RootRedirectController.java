package is.hi.feedme.controller;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * HTTP route for requests to /
 * 
 * This is only here to reroute to /recipes
 * if people try to go to the root of the site
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RootRedirectController {

    @RequestMapping("/")
    void redirect(HttpServletResponse res) throws IOException {
        res.sendRedirect("/recipes");
    }
    
}
