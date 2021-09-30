package is.hi.feedme.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

@RestController
public class RootController {

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map index() {
        return Collections.singletonMap("TODO", "Find non retarded way for static JSON");
    }
}
