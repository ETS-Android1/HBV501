package is.hi.feedme.controller;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RootController {

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map index() {
        return Collections.singletonMap("TODO", "Find non retarded way for static JSON");
    }
}
