package inproject.controller;


import com.fasterxml.jackson.annotation.JsonView;
import inproject.entity.InstagramAuthUser;
import inproject.service.UserService;
import inproject.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "rest/users")
public class UserController {
    @Autowired
    UserService userService;

    @JsonView(Views.Users.class)
    @RequestMapping(method = RequestMethod.GET)
    public List<InstagramAuthUser> findAll(){
        return userService.findAll();
    }
}
